package com.example.lld.designPatterns.creational.factory;

public class main {
    public static void main(String[] args) {
        NotificationFactory notificationFactory = new NotificationFactory();
        INotification notification1 = notificationFactory.createNotification("SMS");
        notification1.sendNotification();
        INotification notification2 = notificationFactory.createNotification("EMAIL");
        notification2.sendNotification();
    }
}
