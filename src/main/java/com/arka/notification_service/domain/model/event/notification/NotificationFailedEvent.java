package com.arka.notification_service.domain.model.event.notification;

import com.arka.notification_service.domain.model.event.common.DomainEvent;
import com.arka.notification_service.domain.model.valueobject.NotificationId;

import java.time.LocalDateTime;
import java.util.UUID;

public class NotificationFailedEvent implements DomainEvent {
    private final NotificationId notificationId;
    private final UUID userId;
    private final String reason;
    private final LocalDateTime occurredOn;

    public NotificationFailedEvent(NotificationId notificationId, UUID userId, String reason) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.reason = reason;
        this.occurredOn = LocalDateTime.now();
    }

    @Override
    public LocalDateTime occurredOn() {
        return occurredOn;
    }

    public NotificationId getNotificationId() {
        return notificationId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getReason() {
        return reason;
    }
}

