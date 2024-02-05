package com.ed.paymentservice.payment.model.enums;

import java.util.Arrays;

public enum PayMethod {
    CASH, CREDIT_CARD, APP;

    public static PayMethod getPayMethod(String payMethod) {
        return Arrays.stream(PayMethod.values())
                .filter(value -> value.name().equals(payMethod))
                .findAny().orElse(null);
    }
}
