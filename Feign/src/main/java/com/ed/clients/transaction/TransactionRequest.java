package com.ed.clients.transaction;

import java.util.UUID;


public record TransactionRequest(
        UUID from,
        UUID to,
        Integer amount
) {

}
