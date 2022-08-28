package com.techelevator.tenmo.model;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Account {

    @NotNull(message = "Account balance cannot be null.")
    private BigDecimal balance;

    @NotNull(message = "Account ID cannot be null.")
    private int accountID;

    @NotNull(message = "Account user ID cannot be null.")
    private int userID;

    public Account(){};

    public Account(BigDecimal balance){
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}