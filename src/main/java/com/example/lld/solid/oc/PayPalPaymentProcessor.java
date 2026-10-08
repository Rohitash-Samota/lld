package com.example.lld.solid.oc;

public class PayPalPaymentProcessor extends PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing Paypal payment of $" + amount);
    }
}
