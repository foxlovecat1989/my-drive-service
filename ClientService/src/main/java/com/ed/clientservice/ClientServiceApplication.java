package com.ed.clientservice;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import com.ed.clientservice.client.model.enums.Role;
import com.ed.clientservice.client.model.record.ClientCreationRequest;
import com.ed.clientservice.client.service.ClientService;

@SpringBootApplication
@EnableEurekaClient
@AllArgsConstructor
public class ClientServiceApplication {
    private final ClientService clientService;

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            for (int i = 0; i < 10; i++) {
                ClientCreationRequest clientCreationRequest =
                        new ClientCreationRequest(String.valueOf(i), Role.DRIVER);
                clientService.createUser(clientCreationRequest);
            }
            for (int i = 0; i < 10; i++) {
                ClientCreationRequest clientCreationRequest =
                        new ClientCreationRequest(String.valueOf(i), Role.CONSUMER);
                clientService.createUser(clientCreationRequest);
            }
        };
    }

}
