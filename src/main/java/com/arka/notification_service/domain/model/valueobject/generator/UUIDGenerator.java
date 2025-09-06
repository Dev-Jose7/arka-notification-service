package com.arka.notification_service.domain.model.valueobject.generator;

import java.util.UUID;

public class UUIDGenerator extends IdGenerator<UUID>{

    @Override
    public UUID generate() {
        return UUID.randomUUID();
    }

    @Override
    public UUID parse(String id) {
        return UUID.fromString(id);
    }
}
