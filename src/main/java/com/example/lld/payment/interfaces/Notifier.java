package com.example.lld.payment.interfaces;

import com.example.lld.payment.dto.Notify;

public interface Notifier {

    void send(Notify notify);
}