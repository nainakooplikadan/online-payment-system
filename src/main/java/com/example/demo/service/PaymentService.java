package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.model.Payment;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Payment makePayment(String accountNumber, Double amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null || account.getBalance() < amount) {
            return new Payment(accountNumber, amount, "FAILED", LocalDateTime.now());
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        Payment payment = new Payment(accountNumber, amount, "SUCCESS", LocalDateTime.now());
        return paymentRepository.save(payment);
    }

    public List<Payment> getPayments(String accountNumber) {
        return paymentRepository.findByAccountNumber(accountNumber);
    }
}
