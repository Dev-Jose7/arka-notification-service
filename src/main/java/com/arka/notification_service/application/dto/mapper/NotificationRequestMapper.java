package com.arka.notification_service.application.dto.mapper;

import com.arka.notification_service.application.dto.command.TemplateDataCommand;
import com.arka.notification_service.application.dto.query.GetTemplateByNameQuery;
import com.arka.notification_service.application.dto.query.GetUserByIdQuery;
import com.arka.notification_service.infrastructure.adapter.controller.dto.request.NotificationRequest;

public class NotificationRequestMapper {

    public static TemplateDataCommand toTemplateDataCommand(NotificationRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        return new TemplateDataCommand(request.getTemplateData());
    }

    public static GetUserByIdQuery toUserQuery(NotificationRequest request) {
        if (request == null || request.getUserId() == null) {
            throw new IllegalArgumentException("User ID in request cannot be null");
        }
        return new GetUserByIdQuery(request.getUserId());
    }

    public static GetTemplateByNameQuery toTemplateQuery(NotificationRequest request) {
        if (request == null || request.getTemplateName() == null) {
            throw new IllegalArgumentException("Template name cannot be null or blank");
        }
        return new GetTemplateByNameQuery(request.getTemplateName());
    }
}
