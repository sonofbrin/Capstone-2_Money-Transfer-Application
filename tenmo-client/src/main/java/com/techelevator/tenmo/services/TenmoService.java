package com.techelevator.tenmo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.tenmo.model.ErrorMessage;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;


public class TenmoService {
    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();

    public TenmoService(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public User[] listAllUsers() {
        User[] users = null;
        try {
            users = restTemplate.getForObject(baseUrl + "users", User[].class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return users;
    }

    public BigDecimal getBalance(Long id) {
        BigDecimal balance = null;
        try {
            balance = restTemplate.getForObject(baseUrl + id + "/balance", BigDecimal.class);

        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return balance;
    }

    public Transfer sendMoney(Transfer transfer) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Transfer> entity = new HttpEntity<>(transfer, headers);
        Transfer newTransfer = null;

        try {
            newTransfer = restTemplate.postForObject(baseUrl + "/send", entity, Transfer.class);
        } catch ( RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return newTransfer;
    }

    public Transfer requestMoney(Transfer transfer) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Transfer> entity = new HttpEntity<>(transfer, headers);
        Transfer newTransfer = null;
        try {
            newTransfer = restTemplate.postForObject(baseUrl + "/request", entity, Transfer.class);

        }
        catch (HttpServerErrorException.InternalServerError e ) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                ErrorMessage errorResponseBody = mapper.readValue(e.getResponseBodyAsString(),
                        ErrorMessage.class);
                System.out.println(errorResponseBody.getMessage());
            } catch (JsonProcessingException ex) {
                ex.printStackTrace();
            }
        }
        catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return newTransfer;
    }

    public void updateTransfer(Transfer transfer, int transferStatusID) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Transfer> entity = new HttpEntity<>(transfer, headers);
        try {
            restTemplate.put(baseUrl + "/update/" + transferStatusID, entity);
        }
        catch (HttpServerErrorException.InternalServerError e ) {

            try {
                ObjectMapper mapper = new ObjectMapper();
                ErrorMessage errorResponseBody = mapper.readValue(e.getResponseBodyAsString(),
                        ErrorMessage.class);
                System.out.println(errorResponseBody.getMessage());
            } catch (JsonProcessingException ex) {
                ex.printStackTrace();
            }

        }catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
    }

    public Transfer getTransferByID(long transferID) {
        try {
            return restTemplate.getForObject(baseUrl + "/transfer/" + transferID, Transfer.class);

        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return null;
    }

    public Transfer[] listTransferHistory (Long id) {
        Transfer[] transfers = null;
        try {
            transfers = restTemplate.getForObject(baseUrl + id + "/past_transfers", Transfer[].class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transfers;
    }

    public String getUserNameFromAccountId(int userId) {
        String username = "";
        try {
            username = restTemplate.getForObject(baseUrl + "users/" + userId, String.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return username;
    }

    public int getUserIDFromAccount(int accountID) {
        int ID = 0;
        try {
            ID = restTemplate.getForObject(baseUrl + "account/" + accountID, Integer.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return ID;
    }

    public int getAccountIDFromUserID(long userID) {
        int ID = 0;
        try {
            ID = restTemplate.getForObject(baseUrl + "users/account/" + userID, Integer.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return ID;
    }

}