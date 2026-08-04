package com.example.lld.abstractFactory.aws;

import org.springframework.stereotype.Component;

import com.example.lld.abstractFactory.module.MessageQueue;

@Component
public class AwsMessageQueue implements MessageQueue {
    @Override
    public void sendMessage(String message) {
        // simple placeholder implementation
        System.out.println("AwsMessageQueue: sending message: " + message);
    }
}
