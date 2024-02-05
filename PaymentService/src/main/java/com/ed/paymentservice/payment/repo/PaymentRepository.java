package com.ed.paymentservice.payment.repo;

import com.ed.paymentservice.payment.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
