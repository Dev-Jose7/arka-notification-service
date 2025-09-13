package com.arka.notification_service.infrastructure.adapter.persistence.mapper;

import com.arka.notification_service.domain.model.aggregate.Notification;
import com.arka.notification_service.domain.model.entity.NotificationHistory;
import com.arka.notification_service.domain.model.entity.User;
import com.arka.notification_service.domain.model.valueobject.NotificationId;
import com.arka.notification_service.domain.model.valueobject.TemplateId;
import com.arka.notification_service.application.dto.internal.NotificationRaw;
import com.arka.notification_service.infrastructure.adapter.persistence.entity.NotificationEntity;
import com.arka.notification_service.infrastructure.adapter.persistence.entity.TemplateEntity;

import java.util.List;

public class NotificationPersistenceMapper {

    public static Notification toDomain(NotificationEntity entity, User recipient, List<NotificationHistory> historyList) {
        return new Notification(
                NotificationId.fromUUID(entity.getId()),
                TemplateId.fromUUID(entity.getTemplate().getId()),
                recipient,
                entity.getSubject(),
                entity.getBody(),
                entity.getChannel(),
                entity.getState(),
                entity.getAttempts(),
                entity.getErrorMessage(),
                entity.getCreatedAt(),
                entity.getSentAt(),
                historyList
        );
    }

    public static NotificationEntity toEntity(Notification domain) {
        NotificationEntity entity = new NotificationEntity();
        TemplateEntity template = new TemplateEntity();
        template.setId(domain.getTemplateId().getValue());

        entity.setId(domain.getId().getValue());
        entity.setTemplate(template);

        switch (domain.getChannel()) {
            case EMAIL:
                entity.setRecipient(domain.getRecipient().getEmail().getAddress());
                break;

            case WHATSAPP:
            case SMS:
                entity.setRecipient(domain.getRecipient().getPhoneNumber().getNumber());
                break;

            default:
                throw new IllegalArgumentException("Channel unsupported: " + domain.getChannel());
        }

        entity.setChannel(domain.getChannel());
        entity.setSubject(domain.getRenderedSubject());
        entity.setBody(domain.getRenderedBody());
        entity.setState(domain.getState());
        entity.setErrorMessage(domain.getErrorMessage());
        entity.setAttempts(domain.getAttempts());
        entity.setSentAt(domain.getSendDate());
        
        return entity;
    }

}
