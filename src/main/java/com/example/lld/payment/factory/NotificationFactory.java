package com.example.lld.payment.factory;

import com.example.lld.payment.dto.Notifier;
import com.example.lld.payment.enums.NotifyType;

public class NotificationFactory {

    public static Notifier getNotifier(NotifyType type) {

        return switch (type) {
            case SMS -> new SMSNotifier();
            case EMAIL -> new EmailNotifier();
            case WHATS_MESSAGE -> new WhatsAppNotifier();
        };
    }
}