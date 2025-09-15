package com.arka.notification_service.infrastructure.adapter.external.user;

import com.arka.notification_service.application.port.out.UserExternalServicePort;
import com.arka.notification_service.domain.model.entity.User;
import com.arka.notification_service.infrastructure.adapter.external.user.dto.UserResponseDTO;
import com.arka.notification_service.infrastructure.adapter.external.user.mapper.UserResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UserExternalServiceAdapter implements UserExternalServicePort {

    private final RestTemplate restTemplate;
    private final String userServiceBaseUrl;
    private final String userServiceGetByIdPath;

    @Override
    public Optional<User> getUserById(UUID id) {
        try {
            String url = userServiceBaseUrl + userServiceGetByIdPath + id;
            UserResponseDTO response = restTemplate.getForObject(url, UserResponseDTO.class);
            return Optional.ofNullable(UserResponseMapper.toDomain(response));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
