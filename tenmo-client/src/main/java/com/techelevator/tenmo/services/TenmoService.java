package com.techelevator.tenmo.services;


import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.User;
import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;


public class TenmoService {
    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();
//    private HttpHeaders headers = new HttpHeaders();

    public TenmoService(String url) {
        this.baseUrl = url;
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

    // TODO skip straight to HttpEntity<Void>? Not sure if always void
    private HttpHeaders createAuthenticationHeader(AuthenticatedUser currentUser) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        return headers;
    }

    public BigDecimal getBalance(AuthenticatedUser currentUser) {
        BigDecimal balance = null;
        try {
            HttpEntity<Void> entity = new HttpEntity<>(createAuthenticationHeader(currentUser));
            ResponseEntity<BigDecimal> response = restTemplate.exchange(baseUrl + "/account/" +
                            currentUser.getUser().getId() + "/balance", HttpMethod.GET, entity, BigDecimal.class);
            balance = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return balance;

//        try {
//            balance = restTemplate.getForObject(baseUrl + id + "/balance", BigDecimal.class);
//
//        } catch (RestClientResponseException | ResourceAccessException e) {
//            BasicLogger.log(e.getMessage());
//        }
//        return balance;
    }
}