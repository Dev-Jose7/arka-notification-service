package com.arka.notification_service.domain.model.event.notification;

import com.arka.notification_service.domain.model.event.common.DomainEvent;
import com.arka.notification_service.domain.model.valueobject.NotificationId;

import java.time.LocalDateTime;
import java.util.UUID;

public class NotificationRetryEvent implements DomainEvent {
    private final NotificationId notificationId;
    private final UUID userId;
    private final int retryAttempt;
    private final LocalDateTime occurredOn;

    public NotificationRetryEvent(NotificationId notificationId, UUID userId, int retryAttempt) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.retryAttempt = retryAttempt;
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

    public int getRetryAttempt() {
        return retryAttempt;
    }
}
