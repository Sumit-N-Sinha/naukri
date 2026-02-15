package com.naukri.payment;

import org.springframework.stereotype.Component;

@Component
public class MockPaymentGateway implements PaymentGateway {
    @Override
    public boolean processPayment(Double amount, String paymentDetails) {
        // Simulate payment processing
        return true;
    }
}
