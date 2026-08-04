package com.example.lld.notificationSender;

import com.example.lld.notificationSender.concrete.EmailService;
import com.example.lld.notificationSender.concrete.PushService;
import com.example.lld.notificationSender.concrete.SMSService;

public class NotifyFactory {
    public NotifyInterface getNotify(ServiceType serviceType) {
        if (serviceType == null) {
            return null;
        }
        switch (serviceType) {
            case ServiceType.SMS:
                return new SMSService();
            case ServiceType.EMAIL:
                return new EmailService();
            case ServiceType.PUSH:
                return new PushService();
            default:
                return null;
        }
    }
}
