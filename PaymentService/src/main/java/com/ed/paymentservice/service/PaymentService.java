package com.ed.paymentservice.service;

import com.ed.clients.transaction.TransactionClient;
import com.ed.clients.transaction.TransactionRequest;
import com.ed.clients.transaction.TransactionResponse;
import com.ed.paymentservice.domain.Payment;
import com.ed.paymentservice.domain.PaymentState;
import com.ed.paymentservice.record.PaymentRequest;
import com.ed.paymentservice.repo.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final TransactionClient transactionClient;

    public Payment executePayment(PaymentRequest paymentRequest) {
        ResponseEntity<TransactionResponse> response = executeTransaction(paymentRequest);
        boolean isSuccess = response.getStatusCode() == HttpStatus.CREATED;

        return savePaymentToDB(paymentRequest, isSuccess);
    }

    private Payment savePaymentToDB(PaymentRequest paymentRequest, boolean isSuccess) {
        Payment newPayment = Payment.builder()
                .paymentId(paymentRequest.paymentId())
                .clientId(paymentRequest.clientId())
                .driverId(paymentRequest.driverId())
                .distance(paymentRequest.distance())
                .rate(paymentRequest.rate())
                .coupon(paymentRequest.coupon())
                .discount(paymentRequest.discount())
                .payMethod(paymentRequest.payMethod())
                .paymentState(isSuccess ? PaymentState.SUCCESS : PaymentState.FAIL)
                .build();

        return paymentRepository.save(newPayment);
    }

    private ResponseEntity<TransactionResponse> executeTransaction(PaymentRequest paymentRequest) {
        int amount = calculateAmount(paymentRequest);
        TransactionRequest transactionRequest = new TransactionRequest(
                paymentRequest.clientId(), paymentRequest.driverId(), amount);

        return transactionClient.transaction(transactionRequest);
    }

    private int calculateAmount(PaymentRequest paymentRequest) {
        return (int) Math.ceil(
                paymentRequest.distance()
                        * paymentRequest.rate()
                        * paymentRequest.discount());
    }
}
