package com.arka.notification_service.application.dto.query;

import java.util.UUID;

public class GetUserByIdQuery {

    private final UUID userId;

    public GetUserByIdQuery(UUID userId) {
        this.userId = userId;
    }

    public UUID getId() {
        return this.userId;
    }

    @Override
    public String toString() {
        return "GetUserByIdQuery{" +
                "userId=" + userId +
                '}';
    }
}
