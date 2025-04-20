package com.example.lab4.services;

import com.example.lab4.annotations.DistributedLock;
import com.example.lab4.entities.Account;
import com.example.lab4.repo.AccountRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private static final String ACCOUNTS_CACHE_KEY = "accounts:all";
    private static final Duration CACHE_TTL = Duration.ofMinutes(5);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private Redis redisClient;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Account> getAllAccounts() {
        try {
            String cachedJson = redisClient.get(ACCOUNTS_CACHE_KEY);
            if (cachedJson != null) {
                logger.info("Cache hit – returning bank accounts from Redis");
                return objectMapper.readValue(cachedJson, new TypeReference<List<Account>>() {});
            }
        } catch (Exception e) {
            logger.error("Error reading bank accounts from Redis", e);
        }

        logger.info("Cache miss – querying database for bank accounts");
        List<Account> accounts = accountRepository.findAll();

        try {
            String json = objectMapper.writeValueAsString(accounts);
            redisClient.set(ACCOUNTS_CACHE_KEY, json, CACHE_TTL);
            logger.info("Bank accounts cached with TTL: {}", CACHE_TTL);
        } catch (Exception e) {
            logger.error("Error writing bank accounts to Redis", e);
        }

        return accounts;
    }

    @DistributedLock(keyPrefix = "bank:account:lock", keyIdentifierExpression = "#accountId", leaseTime = 10, timeUnit = TimeUnit.SECONDS)
    public String simulateMoneyTransfer(Long accountId) {
        try {
            System.out.println("Transferring money from account ID: " + accountId + " by " + Thread.currentThread().getName());
            Thread.sleep(10000); // Simulate processing delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Transfer complete for account: " + accountId;
    }
}
