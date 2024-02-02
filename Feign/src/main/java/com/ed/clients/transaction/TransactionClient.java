package com.ed.clients.transaction;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "transaction-service",
        url = "${clients.transaction.url}"
)
public interface TransactionClient {
    @PostMapping("api/v1/transaction")
    ResponseEntity<TransactionResponse> transaction(@RequestBody TransactionRequest request);
}
