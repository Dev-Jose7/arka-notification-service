package com.arka.notification_service.domain.model.event.common;

import java.time.LocalDateTime;

public interface DomainEvent {
    LocalDateTime occurredOn();
}
