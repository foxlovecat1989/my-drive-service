package userservice.driver.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import userservice.driver.domain.Driver;
import userservice.driver.record.DriverQueryResponse;
import userservice.driver.service.DriverService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/driver")
@AllArgsConstructor
@Slf4j
public class DriverController {
    private final DriverService service;

    @GetMapping("{driverId}")
    public ResponseEntity<DriverQueryResponse> getDriverInfo(@PathVariable String driverId) {
        log.info("driverId: {}", driverId);
        Optional<Driver> driverOpt = service.getDriver(UUID.fromString(driverId));

        if (driverOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Driver driver = driverOpt.get();
        DriverQueryResponse response = new DriverQueryResponse(
                driver.getDriverId(), driver.getName(), driver.getAccountId());


        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
