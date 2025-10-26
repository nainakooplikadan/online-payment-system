package com.example.demo.controller;

import com.example.demo.model.Payment;
import com.example.demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Make a payment
    @PostMapping("/make")
    public Payment makePayment(@RequestParam String accountNumber,
                               @RequestParam Double amount) {
        return paymentService.makePayment(accountNumber, amount);
    }

    // Get payment history
    @GetMapping("/{accountNumber}")
    public List<Payment> getPayments(@PathVariable String accountNumber) {
        return paymentService.getPayments(accountNumber);
    }
}
