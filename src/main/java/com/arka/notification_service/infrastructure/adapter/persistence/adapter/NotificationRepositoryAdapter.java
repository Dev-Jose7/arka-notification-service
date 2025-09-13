package com.arka.notification_service.infrastructure.adapter.persistence.adapter;

import com.arka.notification_service.application.port.out.NotificationRepositoryPort;
import com.arka.notification_service.domain.model.aggregate.Notification;
import com.arka.notification_service.infrastructure.adapter.persistence.entity.NotificationEntity;
import com.arka.notification_service.infrastructure.adapter.persistence.mapper.NotificationPersistenceMapper;
import com.arka.notification_service.infrastructure.adapter.persistence.repository.JpaNotificationRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class NotificationRepositoryAdapter implements NotificationRepositoryPort {

    private final JpaNotificationRepository notificationRepository;

    @Override
    public Notification save(Notification notification) {
        NotificationEntity entity = NotificationPersistenceMapper.toEntity(notification);
        NotificationEntity savedEntity = notificationRepository.save(entity);

        return NotificationPersistenceMapper.toDomain(savedEntity, notification.getRecipient(), notification.getHistory());
    }
}
