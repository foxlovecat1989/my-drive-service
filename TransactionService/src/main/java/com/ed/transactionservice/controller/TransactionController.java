package com.ed.transactionservice.controller;

import com.ed.transactionservice.dto.TransactionRequest;
import com.ed.transactionservice.dto.TransactionResultResponse;
import com.ed.transactionservice.model.Transaction;
import com.ed.transactionservice.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController("transaction/api/v1")
@AllArgsConstructor
@Slf4j
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("transaction")
    public ResponseEntity<TransactionResultResponse> transaction(@RequestBody TransactionRequest request) {
        log.info("Get request: {}", request);
        Transaction transaction = transactionService.transaction(request);
        boolean success = Optional.ofNullable(transaction).isPresent();
        TransactionResultResponse transactionResultResponse =
                new TransactionResultResponse(success);
        log.info("Get transactionResultResponse: {}", transactionResultResponse);
        // TODO: error handle

        return new ResponseEntity<>(transactionResultResponse, HttpStatus.CREATED);
    }
}
