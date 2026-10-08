package com.example.lld.designPatterns.creational.factory;

public class SMSNotification implements INotification {
    @Override
    public void sendNotification() {
        System.out.println("Sending an SMS notification");
    }
}
