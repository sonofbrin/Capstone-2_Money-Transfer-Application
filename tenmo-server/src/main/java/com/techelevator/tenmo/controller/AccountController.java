//package com.techelevator.tenmo.controller;
//
//import com.techelevator.tenmo.dao.AccountDao;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.math.BigDecimal;
//
//@RestController
//@RequestMapping(path = "/account")
//@PreAuthorize("isAuthenticated()")
//public class AccountController {
//    private AccountDao accountDao;
//
//    public AccountController(AccountDao accountDao) {
//        this.accountDao = accountDao;
//    }
//
//    @RequestMapping(path = "/{id}/balance", method = RequestMethod.GET)
//    public BigDecimal getBalance(@PathVariable int id) {
//        return accountDao.getBalance(id);
//    }
//}
