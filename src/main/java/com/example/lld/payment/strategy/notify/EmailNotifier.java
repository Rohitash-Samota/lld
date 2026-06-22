package com.example.lld.payment.strategy.notify;

import com.example.lld.payment.dto.Notify;
import com.example.lld.payment.interfaces.Notifier;

public class EmailNotifier implements Notifier {

    @Override
    public void send(Notify notify) {
        System.out.println("Email Sent : " + notify.getMessage());
    }
}