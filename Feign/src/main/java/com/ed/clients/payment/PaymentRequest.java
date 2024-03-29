package com.ed.clients.payment;


import java.util.UUID;

public record PaymentRequest (
        UUID paymentId,
        UUID clientId,
        UUID driverId,
        Integer distance,
        Float rate,
        String coupon,
        Float discount,
        PayMethod payMethod
) {
}
