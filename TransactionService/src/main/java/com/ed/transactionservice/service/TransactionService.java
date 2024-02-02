package com.ed.transactionservice.service;


import com.ed.clients.transaction.TransactionRequest;
import com.ed.transactionservice.domain.Transaction;
import com.ed.transactionservice.repo.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository repository;

    public Transaction transaction(TransactionRequest request) {
        // TODO: execute a transaction from A to B
        Transaction newTransaction = Transaction.builder()
                .transactionId(UUID.randomUUID())
                .fromId(request.from())
                .toId(request.to())
                .amount(request.amount())
                .build();

        return repository.save(newTransaction);
    }

    public List<Transaction> findAll() {
        return repository.findAll();
    }
}
