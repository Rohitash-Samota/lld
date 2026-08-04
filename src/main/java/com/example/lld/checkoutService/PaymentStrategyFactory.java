package com.example.lld.checkoutService;

import com.example.lld.checkoutService.enums.PaymentMethod;
import com.example.lld.checkoutService.strategy.CardPayment;
import com.example.lld.checkoutService.strategy.NetBankingPayment;
import com.example.lld.checkoutService.strategy.UpiPayment;
import com.example.lld.checkoutService.strategy.WalletPayment;

public final class PaymentStrategyFactory {

    public PaymentStrategy getPaymentStrategy(PaymentMethod paymentMethod) {
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Payment method cannot be null");
        }

        return switch (paymentMethod) {
            case CARD -> new CardPayment();
            case UPI -> new UpiPayment();
            case NET_BANKING -> new NetBankingPayment();
            case WALLET -> new WalletPayment();
        };
    }
}