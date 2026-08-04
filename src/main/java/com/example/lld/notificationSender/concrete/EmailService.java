package com.example.lld.notificationSender.concrete;

import org.springframework.stereotype.Service;

import com.example.lld.notificationSender.NotifyInterface;

@Service
public class EmailService implements NotifyInterface {
    @Override
    public String sendMessage(String message) {
        return message;
    }
}
