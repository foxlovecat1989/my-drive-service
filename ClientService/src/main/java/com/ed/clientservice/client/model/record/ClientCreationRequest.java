package com.ed.clientservice.client.model.record;

import com.ed.clientservice.client.model.enums.Role;

public record ClientCreationRequest(
        String name,
        Role role
) {
}
