package com.arka.notification_service.domain.model.valueobject;

import com.arka.notification_service.domain.model.valueobject.base.IdBase;

import java.util.UUID;

public class NotificationId extends IdBase<UUID> {

    private NotificationId(UUID value) {
        super(value);
    }

    public static NotificationId generate() {
        return new NotificationId(generateValue());
    }

    public static NotificationId fromString(String id) {
        return new NotificationId(parseValue(id));
    }

    public static NotificationId fromUUID(UUID id) {
        return new NotificationId(id);
    }
}

