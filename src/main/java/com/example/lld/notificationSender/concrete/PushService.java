package com.example.lld.notificationSender.concrete;

import com.example.lld.notificationSender.NotifyInterface;

public class PushService implements NotifyInterface {
    @Override
    public String sendMessage(String message) {
        return message;
    }
}
