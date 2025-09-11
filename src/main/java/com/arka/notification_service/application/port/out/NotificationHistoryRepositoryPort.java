package com.arka.notification_service.application.port.out;

import com.arka.notification_service.domain.model.entity.NotificationHistory;
import com.arka.notification_service.domain.model.enums.NotificationState;
import com.arka.notification_service.domain.model.valueobject.NotificationId;

import java.util.List;

public interface NotificationHistoryRepositoryPort {
    List<NotificationHistory> findAllByNotificationId(NotificationId id);
    List<NotificationHistory> findAllByState(NotificationState state);
    void save(NotificationHistory history);
}
