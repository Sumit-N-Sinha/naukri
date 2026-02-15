package com.naukri.service;

import com.naukri.payment.PaymentGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentService {
    @Autowired
    private Map<String, PaymentGateway> paymentGateways;

    public boolean pay(Double amount, String paymentDetails, String gatewayName) {
        PaymentGateway gateway = paymentGateways.get(gatewayName);
        if (gateway == null) {
            throw new IllegalArgumentException("Payment gateway not found: " + gatewayName);
        }
        return gateway.processPayment(amount, paymentDetails);
    }
}
