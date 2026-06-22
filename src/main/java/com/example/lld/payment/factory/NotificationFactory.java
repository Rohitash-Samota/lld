package com.example.lld.payment.factory;

import com.example.lld.payment.enums.NotifyType;
import com.example.lld.payment.interfaces.Notifier;
import com.example.lld.payment.strategy.notify.EmailNotifier;
import com.example.lld.payment.strategy.notify.SMSNotifier;
import com.example.lld.payment.strategy.notify.WhatsAppNotifier;

public class NotificationFactory {

    public static Notifier getNotifier(NotifyType type) {

        return switch (type) {
            case SMS -> new SMSNotifier();
            case EMAIL -> new EmailNotifier();
            case WHATS_MESSAGE -> new WhatsAppNotifier();
        };
    }
}