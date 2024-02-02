package userservice.driver.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import userservice.account.domain.Account;
import userservice.account.service.AccountService;
import userservice.driver.domain.Driver;
import userservice.driver.domain.DriverState;
import userservice.driver.record.DriverCreationRequest;
import userservice.driver.repo.DriverRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class DriverService {
    private final DriverRepository driverRepository;
    private final AccountService accountService;

    public Optional<Driver> getDriver(UUID driverId) {
        return driverRepository.findByDriverId(driverId);
    }

    public void createDriver(DriverCreationRequest request) {
        log.info("Get request to create a driver: {}", request);
        UUID userId = UUID.randomUUID();
        Account newAccount = accountService.createAccount(userId);
        Driver newDriver = Driver.builder()
                .driverId(userId)
                .name(request.name())
                .accountId(newAccount.getAccountId())
                .state(DriverState.NORMAL)
                .build();
        log.info("Create a driver: {}", newDriver);

        driverRepository.save(newDriver);
    }
}
