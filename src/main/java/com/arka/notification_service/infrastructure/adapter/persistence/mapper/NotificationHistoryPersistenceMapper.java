package com.arka.notification_service.infrastructure.adapter.persistence.mapper;

import com.arka.notification_service.domain.model.aggregate.Notification;
import com.arka.notification_service.domain.model.aggregate.Template;
import com.arka.notification_service.domain.model.entity.NotificationHistory;
import com.arka.notification_service.domain.model.valueobject.NotificationId;
import com.arka.notification_service.infrastructure.adapter.persistence.entity.NotificationEntity;
import com.arka.notification_service.infrastructure.adapter.persistence.entity.NotificationHistoryEntity;

public class NotificationHistoryPersistenceMapper {

    public static NotificationHistory toDomain(NotificationHistoryEntity entity) {
        return new NotificationHistory(
                entity.getId(),
                NotificationId.fromUUID(entity.getNotification().getId()),
                entity.getState(),
                entity.getAttemptsNumber(),
                entity.getSentAt()
        );
    }

    public static NotificationHistoryEntity toEntity(NotificationHistory domain, NotificationEntity notificationEntity) {
        NotificationHistoryEntity entity = new NotificationHistoryEntity();

        entity.setId(domain.getId());
        entity.setNotification(notificationEntity);
        entity.setState(domain.getState());
        entity.setAttemptsNumber(domain.getAttemptNumber());
        entity.setErrorMessage(domain.getErrorMessage());
        entity.setSentAt(domain.getSentAt());

        return entity;
    }
}
