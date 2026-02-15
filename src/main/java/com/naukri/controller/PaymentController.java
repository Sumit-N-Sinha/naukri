package com.naukri.controller;

import com.naukri.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<String> makePayment(@RequestParam Double amount, @RequestParam String paymentDetails, @RequestParam String gatewayName) {
        boolean success;
        try {
            success = paymentService.pay(amount, paymentDetails, gatewayName);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        if (success) {
            return ResponseEntity.ok("Payment successful");
        } else {
            return ResponseEntity.status(500).body("Payment failed");
        }
    }
}
