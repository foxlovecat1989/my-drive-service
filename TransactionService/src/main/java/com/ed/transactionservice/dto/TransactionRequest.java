package com.ed.transactionservice.dto;

import java.math.BigDecimal;
import java.util.UUID;


public record TransactionRequest(
        UUID from,
        UUID to,
        BigDecimal amount
) {

}
