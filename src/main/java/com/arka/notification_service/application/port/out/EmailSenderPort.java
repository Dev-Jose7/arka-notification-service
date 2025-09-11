package com.arka.notification_service.application.port.out;

import com.arka.notification_service.domain.model.aggregate.Notification;

public interface EmailSenderPort {
    void send(Notification notification);
}
