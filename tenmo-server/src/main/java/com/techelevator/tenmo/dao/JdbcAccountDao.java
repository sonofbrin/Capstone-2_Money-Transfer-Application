package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM account;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Account account = mapRowToAccount(results);
            accounts.add(account);
        }
        return accounts;
    }



    @Override
    public BigDecimal getBalance(int userId) {
        String sql = "SELECT balance FROM account " +
                     "WHERE user_id = ?;";
        return jdbcTemplate.queryForObject(sql, BigDecimal.class, userId);
    }

    @Override
    public int getAccountId(int userID) {
        int accountID = 0 ;
        String sql = "SELECT account_id FROM account " +
                "WHERE user_id = ?;";

        accountID = jdbcTemplate.queryForObject(sql, Integer.class, userID);
        return accountID;

    }

    @Override
    public int getUserId(int fromAccountID) {
        int userID = 0;
        String sql = "SELECT user_id FROM account " +
                "WHERE account_id = ?;";
        userID = jdbcTemplate.queryForObject(sql, Integer.class, fromAccountID );
        return userID;
    }

    private Account mapRowToAccount(SqlRowSet result){
        Account account = new Account();
        account.setBalance(result.getBigDecimal("balance"));
        account.setAccountID(result.getInt("account_id"));
        account.setUserID(result.getInt("user_id"));
        return account;
    }

}
