package com.ed.transactionservice;

import com.ed.transactionservice.dto.TransactionRequest;
import com.ed.transactionservice.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootApplication
@EnableEurekaClient
@AllArgsConstructor
public class TransactionServiceApplication {
    private final TransactionService transactionService;
    public static void main(String[] args) {
        SpringApplication.run(TransactionServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            for (int i = 0; i < 10; i++) {
                TransactionRequest request = new TransactionRequest(UUID.randomUUID(), UUID.randomUUID(), BigDecimal.valueOf(i));
                transactionService.transaction(request);
            }

            transactionService.findAll().forEach(System.out::println);
        };
    }
}
