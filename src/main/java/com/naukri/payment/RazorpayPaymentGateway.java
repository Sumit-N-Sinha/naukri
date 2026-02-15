package com.naukri.payment;

import org.springframework.stereotype.Component;

@Component
public class RazorpayPaymentGateway implements PaymentGateway {
    @Override
    public boolean processPayment(Double amount, String paymentDetails) {
        // Simulate Razorpay payment processing
        return true;
    }
}
