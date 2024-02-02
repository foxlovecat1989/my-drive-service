package userservice;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import userservice.client.record.ClientCreationRequest;
import userservice.client.service.ClientService;
import userservice.driver.record.DriverCreationRequest;
import userservice.driver.service.DriverService;

@SpringBootApplication
@EnableEurekaClient
@AllArgsConstructor
public class UserServiceApplication {
    private final ClientService clientService;
    private final DriverService driverService;

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            for (int i = 0; i < 10; i++) {
                ClientCreationRequest clientCreationRequest =
                        new ClientCreationRequest(String.valueOf(i));
                clientService.createClient(clientCreationRequest);
            }
            for (int i = 0; i < 10; i++) {
                DriverCreationRequest driverCreationRequest =
                        new DriverCreationRequest(String.valueOf(i));
                driverService.createDriver(driverCreationRequest);
            }
        };
    }

}
