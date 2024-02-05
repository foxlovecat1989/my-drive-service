package com.ed.clients.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "payment-service",
        url = "${clients.payment.url}"
)
public interface PaymentServiceClient {
    @PostMapping("api/v1/payment")
    ResponseEntity<PaymentResponse> transaction(@RequestBody PaymentRequest request);
}
