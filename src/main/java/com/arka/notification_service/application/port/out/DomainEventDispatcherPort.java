package com.arka.notification_service.application.port.out;

import com.arka.notification_service.domain.model.event.common.DomainEvent;

public interface DomainEventDispatcherPort {
    void dispatch(DomainEvent event);
}
