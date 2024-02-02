package com.ed.transactionservice.controller;

import com.ed.clients.transaction.TransactionRequest;
import com.ed.clients.transaction.TransactionResponse;
import com.ed.transactionservice.domain.Transaction;
import com.ed.transactionservice.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/transaction")
@AllArgsConstructor
@Slf4j
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponse> transaction(@RequestBody TransactionRequest request) {
        log.info("Get request: {}", request);
        Transaction transaction = transactionService.transaction(request);
        boolean success = Optional.ofNullable(transaction).isPresent();
        TransactionResponse transactionResponse =
                new TransactionResponse(success);
        log.info("Get transactionResultResponse: {}", transactionResponse);
        // TODO: error handle

        return new ResponseEntity<>(transactionResponse, HttpStatus.CREATED);
    }
}
