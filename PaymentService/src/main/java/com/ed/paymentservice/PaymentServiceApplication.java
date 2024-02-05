package com.ed.paymentservice;

import com.ed.clients.payment.PayMethod;
import com.ed.clients.payment.PaymentRequest;
import com.ed.paymentservice.payment.model.entity.Payment;
import com.ed.paymentservice.payment.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.ed.clients"
)
@AllArgsConstructor
public class PaymentServiceApplication {
    private final PaymentService paymentService;

    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            for (int i = 0; i < 10; i++) {
                PaymentRequest paymentRequest = new PaymentRequest(
                        UUID.randomUUID(),
                        UUID.randomUUID(),
                        UUID.randomUUID(),
                        100 * i,
                        1.0f,
                        null,
                        0.9f,
                        PayMethod.CREDIT_CARD);
                Payment payment = paymentService.executePayment(paymentRequest);
                System.out.println(payment);
            }
        };
    }
}
