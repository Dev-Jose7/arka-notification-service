package com.arka.notification_service.application.port.in;

import com.arka.notification_service.application.dto.command.TemplateDataCommand;
import com.arka.notification_service.domain.model.aggregate.Template;
import com.arka.notification_service.domain.model.entity.User;

public interface SendEmailNotificationUseCase {
    void execute(TemplateDataCommand command, Template template, User user);
}
