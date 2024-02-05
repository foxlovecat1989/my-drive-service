package com.ed.paymentservice.payment.controller;

import com.ed.clients.payment.PaymentRequest;
import com.ed.clients.payment.PaymentResponse;
import com.ed.paymentservice.payment.model.entity.Payment;
import com.ed.paymentservice.payment.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("ap1/v1/payment")
@AllArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> executePayment(@RequestBody PaymentRequest request) {
        Payment payment = paymentService.executePayment(request);
        PaymentResponse paymentResponse = new PaymentResponse(Optional.ofNullable(payment).isPresent());

        return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
    }
}
