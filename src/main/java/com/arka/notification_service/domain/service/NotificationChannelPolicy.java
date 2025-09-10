package com.arka.notification_service.domain.service;

import com.arka.notification_service.domain.model.entity.User;
import com.arka.notification_service.domain.model.enums.NotificationChannel;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NotificationChannelPolicy {

    private final Integer START_HOUR = 8;
    private final Integer FINISH_HOUR = 20;

    public List<NotificationChannel> determinateValidChannels(User user, LocalDateTime sendDate) {
        if (!user.canReceiveNotifications()) {
            return Collections.emptyList();
        }

        return user.getNotificationPreferences().stream()
                .filter(channel -> isChannelEnabledAt(channel, sendDate))
                .collect(Collectors.toList());
    }

    public boolean isChannelEnabledAt(NotificationChannel channel, LocalDateTime sendDate) {
        Integer hour = sendDate.getHour();

        switch (channel) {
            case EMAIL:
            case SMS:
            case WHATSAPP:
            case PUSH:
                return hour >= START_HOUR && hour <= FINISH_HOUR;
            default:
                return false;
        }
    }
}
