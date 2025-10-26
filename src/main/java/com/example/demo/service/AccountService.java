package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.model.Transaction;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    // Create account
    public Account createAccount(Account account) {
        account.setBalance(0.0); // initial balance
        return accountRepository.save(account);
    }

    // Get accounts by user
    public List<Account> getAccountsByUser(Long userId) {
        return accountRepository.findByUserId(userId);
    }

    // Transfer funds
    public String transferFunds(String fromAccount, String toAccount, Double amount) {
        Account sender = accountRepository.findByAccountNumber(fromAccount);
        Account receiver = accountRepository.findByAccountNumber(toAccount);

        if (sender == null || receiver == null) return "Account not found";
        if (sender.getBalance() < amount) return "Insufficient balance";

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        accountRepository.save(sender);
        accountRepository.save(receiver);

        // Save transaction
        Transaction transaction = new Transaction(fromAccount, toAccount, amount, LocalDateTime.now());
        transactionRepository.save(transaction); // <-- this uses the repository

        return "Transfer successful";
    }
}
