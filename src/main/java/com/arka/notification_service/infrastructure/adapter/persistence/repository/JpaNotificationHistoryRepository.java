package com.arka.notification_service.infrastructure.adapter.persistence.repository;

import com.arka.notification_service.domain.model.enums.NotificationState;
import com.arka.notification_service.infrastructure.adapter.persistence.entity.NotificationHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaNotificationHistoryRepository extends JpaRepository<NotificationHistoryEntity, UUID> {
    List<NotificationHistoryEntity> findAllByNotificationId(UUID notificationId);
    List<NotificationHistoryEntity> findAllByState(NotificationState state);
}
