package com.ed.paymentservice.transaction.repo;


import com.ed.paymentservice.transaction.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
