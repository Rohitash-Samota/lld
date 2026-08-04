package com.example.lld.abstractFactory.aws;

import com.example.lld.abstractFactory.module.MessageQueue;

public class AwsMessageQueue implements MessageQueue {
    @Override
    public MessageQueue sendMessage(String message){
        return new MessageQueue();
    }
}
