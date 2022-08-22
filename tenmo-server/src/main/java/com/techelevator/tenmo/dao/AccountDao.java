package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

public interface AccountDao {

    Account getAccountByUserId(Long userId);

    void updateBalance(Account account);
}
