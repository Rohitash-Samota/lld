package com.example.lld.payment.strategy.notify;

public class EmailNotifier implements Notifier {

    @Override
    public void send(Notify notify) {
        System.out.println("Email Sent : " + notify.getMessage());
    }
}