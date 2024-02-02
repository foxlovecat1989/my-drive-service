package userservice.client.record;

import java.util.UUID;

public record ClientQueryResponse(
        UUID clientId,
        String name,
        UUID accountId
) {
}
