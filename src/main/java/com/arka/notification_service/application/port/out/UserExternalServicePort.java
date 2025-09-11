package com.arka.notification_service.application.port.out;

import com.arka.notification_service.domain.model.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserExternalServicePort {
    Optional<User> getUserById(UUID id);
}
