package com.arka.notification_service.domain.model.aggregate;

import com.arka.notification_service.domain.model.entity.NotificationHistory;
import com.arka.notification_service.domain.model.entity.User;
import com.arka.notification_service.domain.model.enums.NotificationChannel;
import com.arka.notification_service.domain.model.enums.NotificationState;
import com.arka.notification_service.domain.model.event.common.DomainEvent;
import com.arka.notification_service.domain.model.event.notification.NotificationFailedEvent;
import com.arka.notification_service.domain.model.event.notification.NotificationRetryEvent;
import com.arka.notification_service.domain.model.event.notification.NotificationSentEvent;
import com.arka.notification_service.domain.model.valueobject.NotificationId;
import com.arka.notification_service.domain.model.valueobject.TemplateId;

import java.time.LocalDateTime;
import java.util.*;

public class Notification {

    private final NotificationId id;
    private final TemplateId templateId;
    private final User recipient;
    private final String renderedSubject;
    private final String renderedBody;
    private final NotificationChannel channel;
    private NotificationState state;
    private int attempts;
    private String errorMessage;
    private final LocalDateTime creationDate;
    private LocalDateTime sendDate;
    private final List<NotificationHistory> history;
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    private static final int MAX_RETRIES = 3;

    public Notification(NotificationId id, TemplateId templateId, User recipient, String renderedSubject,
                        String renderedBody, NotificationChannel channel, NotificationState state,
                        int attempts, String errorMessage, LocalDateTime creationDate,
                        LocalDateTime sendDate, List<NotificationHistory> history) {

        if (id == null) throw new IllegalArgumentException("ID value cannot be null.");
        if (templateId == null) throw new IllegalArgumentException("Template ID value cannot be null.");
        if (recipient == null) throw new IllegalArgumentException("Recipient value cannot be null.");
        if (renderedSubject == null || renderedSubject.isBlank())
            throw new IllegalArgumentException("Subject value cannot be null.");
        if (renderedBody == null || renderedBody.isBlank())
            throw new IllegalArgumentException("Body value cannot be null.");
        if (channel == null) throw new IllegalArgumentException("Channel value cannot be null.");
        if (state == null) throw new IllegalArgumentException("State cannot be null.");
        if (attempts < 0) throw new IllegalArgumentException("Attempts cannot be negative.");
        if (creationDate == null) throw new IllegalArgumentException("Creation date cannot be null.");
        if (history == null) throw new IllegalArgumentException("History list cannot be null.");

        this.id = id;
        this.templateId = templateId;
        this.recipient = recipient;
        this.renderedSubject = renderedSubject;
        this.renderedBody = renderedBody;
        this.channel = channel;
        this.state = state;
        this.attempts = attempts;
        this.errorMessage = errorMessage;
        this.creationDate = creationDate;
        this.sendDate = sendDate;
        this.history = new ArrayList<>(history);
    }

    public Notification(NotificationId id, TemplateId templateId, User recipient,
                        String renderedSubject, String renderedBody, NotificationChannel channel) {

        this(id, templateId, recipient, renderedSubject, renderedBody, channel,
                NotificationState.PENDING, 0, null,
                LocalDateTime.now(), null, new ArrayList<>());
    }

    public void markAsPending() {
        if (this.state != NotificationState.FAILED && this.state != NotificationState.RETRYING) {
            throw new IllegalStateException("Only failed or retrying notifications can be marked as pending.");
        }
        this.state = NotificationState.PENDING;
    }

    public void markAsProcessing() {
        if (this.state != NotificationState.PENDING) {
           throw new IllegalStateException("Cannot transition to PROCESSING from state: " + this.state);
        }

        this.state = NotificationState.PROCESSING;
    }

    public void markAsSent() {
        if (this.state == NotificationState.SENT) {
            throw new IllegalStateException("Notification is already marked as sent.");
        }
        if (this.state == NotificationState.FAILED) {
            throw new IllegalStateException("Cannot mark a failed notification as sent.");
        }
        this.state = NotificationState.SENT;
        this.sendDate = LocalDateTime.now();

        this.domainEvents.add(new NotificationSentEvent(
                this.id,
                this.recipient.getId(),
                this.channel,
                this.renderedBody,
                this.sendDate));
    }

    public void markAsFailed(String reason) {
        if (this.state == NotificationState.SENT) {
            throw new IllegalStateException("Cannot fail a notification that has already been sent.");
        }
        this.state = NotificationState.FAILED;
        this.errorMessage = reason;

        this.domainEvents.add(new NotificationFailedEvent(
                this.id,
                this.recipient.getId(),
                this.errorMessage));
    }

    public void markAsRetry() {
        if (this.state != NotificationState.FAILED) {
            throw new IllegalStateException("Only failed notifications can be retried.");
        }

        if (this.attempts >= MAX_RETRIES) {
            throw new IllegalStateException("Maximum retry attempts reached.");
        }

        this.attempts++;
        this.state = NotificationState.RETRYING;

        this.domainEvents.add(new NotificationRetryEvent(
                this.id,
                this.recipient.getId(),
                this.attempts));
    }

    public void addHistory(NotificationHistory historyEntry) {
        if (historyEntry == null) throw new IllegalArgumentException("Notification history cannot be null.");
        this.history.add(historyEntry);
    }

    public List<DomainEvent> extractEvents() {
        List<DomainEvent> events = new ArrayList<>(domainEvents);
        domainEvents.clear();
        return Collections.unmodifiableList(events);
    }

    public NotificationId getId() {
        return id;
    }

    public TemplateId getTemplateId() {
        return templateId;
    }

    public User getRecipient() {
        return recipient;
    }

    public String getRenderedSubject() {
        return renderedSubject;
    }

    public String getRenderedBody() {
        return renderedBody;
    }

    public NotificationChannel getChannel() {
        return channel;
    }

    public NotificationState getState() {
        return state;
    }

    public int getAttempts() {
        return attempts;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public List<NotificationHistory> getHistory() {
        return List.copyOf(history); // Inmutable por seguridad
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notification)) return false;
        Notification that = (Notification) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", templateId=" + templateId +
                ", recipient=" + recipient +
                ", subject='" + renderedSubject + '\'' +
                ", channel=" + channel +
                ", state=" + state +
                ", attempts=" + attempts +
                ", errorMessage='" + errorMessage + '\'' +
                ", creationDate=" + creationDate +
                ", sendDate=" + sendDate +
                '}';
    }
}
