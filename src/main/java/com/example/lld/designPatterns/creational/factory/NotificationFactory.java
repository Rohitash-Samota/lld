package com.example.lld.designPatterns.creational.factory;

public class NotificationFactory {
    public INotification createNotification(String channel) {
        if (channel == null || channel.isEmpty())
            return null;
        if ("SMS".equalsIgnoreCase(channel)) {
            return new SMSNotification();
        } else if ("EMAIL".equalsIgnoreCase(channel)) {
            return new EmailNotification();
        }
        return null;
    }
}
