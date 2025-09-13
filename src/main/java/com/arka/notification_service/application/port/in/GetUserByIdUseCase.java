package com.arka.notification_service.application.port.in;

import com.arka.notification_service.application.dto.query.GetUserByIdQuery;
import com.arka.notification_service.domain.model.entity.User;

public interface GetUserByIdUseCase {
    User execute(GetUserByIdQuery id);
}
