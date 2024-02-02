package userservice.driver.record;

import java.util.UUID;

public record DriverQueryResponse(
        UUID driverId,
        String name,
        UUID accountId
) {
}
