package com.example.lld.designPatterns.creational.factory;

public class EmailNotification implements INotification {
    @Override
    public void sendNotification() {
        System.out.println("Sending an Email notification");
    }
}
