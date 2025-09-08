package com.arka.notification_service.domain.model.event.notification;

import com.arka.notification_service.domain.model.enums.NotificationChannel;
import com.arka.notification_service.domain.model.event.common.DomainEvent;
import com.arka.notification_service.domain.model.valueobject.NotificationId;

import java.time.LocalDateTime;
import java.util.UUID;

public class NotificationSentEvent implements DomainEvent {

    private final NotificationId notificationId;
    private final UUID userId;
    private final NotificationChannel channel;
    private final String contentSummary;
    private final LocalDateTime sendDate;
    private final LocalDateTime occurredOn;

    public NotificationSentEvent(NotificationId notificationId, UUID userId,
                                 NotificationChannel channel, String contentSummary,
                                 LocalDateTime sendDate) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.channel = channel;
        this.contentSummary = contentSummary;
        this.sendDate = sendDate;
        this.occurredOn = LocalDateTime.now();
    }

    public NotificationId getNotificationId() {
        return notificationId;
    }

    public UUID getUserId() {
        return userId;
    }

    public NotificationChannel getChannel() {
        return channel;
    }

    public String getContentSummary() {
        return contentSummary;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    @Override
    public LocalDateTime occurredOn() {
        return occurredOn;
    }

    @Override
    public String toString() {
        return "NotificationSentEvent{" +
                "notificationId=" + notificationId +
                ", userId=" + userId +
                ", channel=" + channel +
                ", contentSummary='" + contentSummary + '\'' +
                ", sendDate=" + sendDate +
                '}';
    }
}
