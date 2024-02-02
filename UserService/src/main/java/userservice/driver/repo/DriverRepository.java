package userservice.driver.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import userservice.driver.domain.Driver;

import java.util.Optional;
import java.util.UUID;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByDriverId(UUID clientId);
}
