package com.arka.notification_service.application.dto.internal;

import com.arka.notification_service.domain.model.enums.NotificationChannel;
import com.arka.notification_service.domain.model.enums.NotificationState;
import com.arka.notification_service.domain.model.valueobject.NotificationId;
import com.arka.notification_service.domain.model.valueobject.TemplateId;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
public class NotificationRaw {

    private final NotificationId id;
    private final TemplateId templateId;
    private final String subject;
    private final String body;
    private final NotificationChannel channel;
    private final NotificationState state;
    private final int attempts;
    private final String errorMessage;
    private final LocalDateTime createdAt;
    private final LocalDateTime sentAt;

    public NotificationRaw(NotificationId id,
                           TemplateId templateId,
                           String subject,
                           String body,
                           NotificationChannel channel,
                           NotificationState state,
                           int attempts,
                           String errorMessage,
                           LocalDateTime createdAt,
                           LocalDateTime sentAt) {

        this.id = Objects.requireNonNull(id);
        this.templateId = Objects.requireNonNull(templateId);
        this.subject = Objects.requireNonNull(subject);
        this.body = Objects.requireNonNull(body);
        this.channel = Objects.requireNonNull(channel);
        this.state = Objects.requireNonNull(state);
        this.attempts = attempts;
        this.errorMessage = errorMessage;
        this.createdAt = Objects.requireNonNull(createdAt);
        this.sentAt = sentAt;
    }
}
