package com.arka.notification_service.infrastructure.adapter.external.user.mapper;

import com.arka.notification_service.domain.model.entity.User;
import com.arka.notification_service.domain.model.valueobject.Email;
import com.arka.notification_service.domain.model.valueobject.PhoneNumber;
import com.arka.notification_service.infrastructure.adapter.external.user.dto.UserResponseDTO;

import java.util.UUID;

public class UserResponseMapper {

    public static User toDomain(UserResponseDTO dto) {
        if (dto == null) return null;

        return new User(
                UUID.fromString(dto.getId()),
                dto.getName(),
                new Email(dto.getEmail()),
                new PhoneNumber(dto.getCountryCode(), dto.getPhoneNumber()),
                dto.isWantsNotifications(),
                dto.isPrefersEmail(),
                dto.isPrefersWhatsapp(),
                dto.isPrefersSms(),
                dto.isPrefersPush()
        );
    }
}
