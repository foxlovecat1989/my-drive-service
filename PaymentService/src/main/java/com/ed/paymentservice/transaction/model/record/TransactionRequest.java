package com.ed.paymentservice.transaction.model.record;

import java.util.UUID;


public record TransactionRequest(
        UUID from,
        UUID to,
        Integer amount
) {

}
