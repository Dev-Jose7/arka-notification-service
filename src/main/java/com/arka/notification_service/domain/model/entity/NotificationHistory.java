package com.arka.notification_service.domain.model.entity;

import com.arka.notification_service.domain.model.enums.NotificationState;
import com.arka.notification_service.domain.model.valueobject.NotificationId;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class NotificationHistory {

    private final UUID id;
    private final NotificationId notificationId;
    private final NotificationState state;
    private final int attemptNumber;
    private final String errorMessage;
    private final LocalDateTime sentAt;

    public NotificationHistory(UUID id, NotificationId notificationId, NotificationState state,
                               int attemptNumber, String errorMessage, LocalDateTime sentAt) {

        if (id == null) throw new IllegalArgumentException("ID cannot be null.");
        if (notificationId == null) throw new IllegalArgumentException("NotificationId cannot be null.");
        if (state == null) throw new IllegalArgumentException("State cannot be null.");
        if (sentAt == null) throw new IllegalArgumentException("SentAt cannot be null.");

        this.id = id;
        this.notificationId = notificationId;
        this.state = state;
        this.attemptNumber = attemptNumber;
        this.errorMessage = errorMessage;
        this.sentAt = sentAt;
    }

    public NotificationHistory(UUID id, NotificationId notificationId, NotificationState state,
                               int attemptNumber, LocalDateTime sentAt) {
        this(id, notificationId, state, attemptNumber, null, sentAt);
    }

    public UUID getId() {
        return id;
    }

    public NotificationId getNotificationId() {
        return notificationId;
    }

    public NotificationState getState() {
        return state;
    }

    public int getAttemptNumber() {
        return attemptNumber;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotificationHistory notificationHistory = (NotificationHistory) o;
        return id.equals(notificationHistory.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "NotificationHistory{" +
                "id=" + id +
                ", notificationId=" + notificationId +
                ", state=" + state +
                ", attemptNumber=" + attemptNumber +
                ", errorMessage='" + errorMessage + '\'' +
                ", sentAt=" + sentAt +
                '}';
    }
}
