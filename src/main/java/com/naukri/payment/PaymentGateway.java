package com.naukri.payment;

public interface PaymentGateway {
    boolean processPayment(Double amount, String paymentDetails);
}
