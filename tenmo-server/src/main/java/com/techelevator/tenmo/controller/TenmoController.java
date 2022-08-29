//package com.techelevator.tenmo.controller;
//
//import com.techelevator.tenmo.dao.AccountDao;
//import com.techelevator.tenmo.model.Account;
//
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import com.techelevator.tenmo.dao.UserDao;
//import com.techelevator.tenmo.model.User;
//
//
//
//import javax.validation.Valid;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//
////@RestController
//public class TenmoController {
//
//    private AccountDao accountDao;
//    private UserDao userDao;
//
//    public TenmoController(AccountDao accountDao, UserDao userDao) {
//        this.accountDao = accountDao;
//        this.userDao = userDao;
//    }
//
//    @RequestMapping(path = "/{id}/balance", method = RequestMethod.GET)
//    public BigDecimal getBalance(@PathVariable("id") int userId) {
//
//        return accountDao.getBalance(userId);
//    }
//
//    @RequestMapping(path = "/users", method = RequestMethod.GET)
//    public List<User> listUsers() {
//        return userDao.findAll();
//    }
//}