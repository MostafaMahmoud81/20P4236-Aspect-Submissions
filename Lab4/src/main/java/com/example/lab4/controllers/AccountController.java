package com.example.lab4.controllers;

import com.example.lab4.annotations.RateLimit;
import com.example.lab4.entities.Account;
import com.example.lab4.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService AccountService;

    @RateLimit(limit = 2, duration = 20, timeUnit = TimeUnit.SECONDS, keyPrefix = "getAccounts")
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(AccountService.getAllAccounts());
    }

    @GetMapping("/access/{accountId}")
    public ResponseEntity<String> processAccount(@PathVariable Long accountId) {
        return ResponseEntity.ok(AccountService.simulateMoneyTransfer(accountId));
    }
}