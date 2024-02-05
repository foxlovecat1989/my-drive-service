package com.ed.clientservice.client.model.record;

import java.util.UUID;

public record ClientQueryResponse(
        UUID clientId,
        String name,
        UUID accountId
) {
}
