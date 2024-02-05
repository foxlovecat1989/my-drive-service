package com.ed.paymentservice.payment.service;

import com.ed.clients.payment.PaymentRequest;
import com.ed.paymentservice.payment.model.entity.Payment;
import com.ed.paymentservice.payment.model.enums.PayMethod;
import com.ed.paymentservice.payment.model.enums.PaymentState;
import com.ed.paymentservice.payment.repo.PaymentRepository;
import com.ed.paymentservice.transaction.model.entity.Transaction;
import com.ed.paymentservice.transaction.model.record.TransactionRequest;
import com.ed.paymentservice.transaction.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final TransactionService transactionService;

    public Payment executePayment(PaymentRequest paymentRequest) {
        Transaction transaction = executeTransaction(paymentRequest);
        boolean isSuccess = Optional.ofNullable(transaction).isPresent();

        return savePaymentToDB(paymentRequest, isSuccess);
    }

    private Payment savePaymentToDB(PaymentRequest paymentRequest, boolean isSuccess) {
        PayMethod payMethod = PayMethod.getPayMethod(paymentRequest.payMethod().name());
        Payment newPayment = Payment.builder()
                .paymentId(paymentRequest.paymentId())
                .clientId(paymentRequest.clientId())
                .driverId(paymentRequest.driverId())
                .distance(paymentRequest.distance())
                .rate(paymentRequest.rate())
                .coupon(paymentRequest.coupon())
                .discount(paymentRequest.discount())
                .payMethod(payMethod)
                .paymentState(isSuccess ? PaymentState.SUCCESS : PaymentState.FAIL)
                .build();

        return paymentRepository.save(newPayment);
    }

    private Transaction executeTransaction(PaymentRequest paymentRequest) {
        int amount = calculateAmount(paymentRequest);
        TransactionRequest transactionRequest = new TransactionRequest(
                paymentRequest.clientId(), paymentRequest.driverId(), amount);

        return transactionService.transaction(transactionRequest);
    }

    private int calculateAmount(PaymentRequest paymentRequest) {
        return (int) Math.ceil(
                paymentRequest.distance()
                        * paymentRequest.rate()
                        * paymentRequest.discount());
    }
}
