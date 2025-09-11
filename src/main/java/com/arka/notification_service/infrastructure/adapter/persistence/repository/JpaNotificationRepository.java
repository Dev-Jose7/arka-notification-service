package com.arka.notification_service.infrastructure.adapter.persistence.repository;

import com.arka.notification_service.infrastructure.adapter.persistence.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaNotificationRepository extends JpaRepository<NotificationEntity, UUID> {
}
