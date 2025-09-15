package com.arka.notification_service.application.usecase.query;

import com.arka.notification_service.application.dto.query.GetUserByIdQuery;
import com.arka.notification_service.application.exception.UserNotFoundException;
import com.arka.notification_service.application.port.in.GetUserByIdUseCase;
import com.arka.notification_service.application.port.out.UserExternalServicePort;
import com.arka.notification_service.domain.model.entity.User;

public class GetUserById implements GetUserByIdUseCase {

    private final UserExternalServicePort userRepository;

    public GetUserById(UserExternalServicePort userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User execute(GetUserByIdQuery id) {
        return userRepository.getUserById(id.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
