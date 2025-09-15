package com.arka.notification_service.infrastructure.adapter.controller;

import com.arka.notification_service.application.dto.command.TemplateDataCommand;
import com.arka.notification_service.application.dto.mapper.NotificationRequestMapper;
import com.arka.notification_service.application.dto.query.GetTemplateByNameQuery;
import com.arka.notification_service.application.dto.query.GetUserByIdQuery;
import com.arka.notification_service.application.port.in.GetTemplateByNameUseCase;
import com.arka.notification_service.application.port.in.GetUserByIdUseCase;
import com.arka.notification_service.application.port.in.SendEmailNotificationUseCase;
import com.arka.notification_service.domain.model.aggregate.Template;
import com.arka.notification_service.domain.model.entity.User;
import com.arka.notification_service.infrastructure.adapter.controller.dto.request.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/api/email/")
public class EmailNotificationController {

    private final SendEmailNotificationUseCase sendEmailNotificationUseCase;
    private final GetTemplateByNameUseCase getTemplateByNameUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;

    @PostMapping("/send")
    public ResponseEntity<Void> notificationExample(@RequestBody NotificationRequest request) {
        TemplateDataCommand toTemplateDataCommand = NotificationRequestMapper.toTemplateDataCommand(request);
        GetTemplateByNameQuery templateQuery = NotificationRequestMapper.toTemplateQuery(request);
        GetUserByIdQuery userQuery = NotificationRequestMapper.toUserQuery(request);

        Template template = getTemplateByNameUseCase.execute(templateQuery);
        User user = getUserByIdUseCase.execute(userQuery);

        sendEmailNotificationUseCase.execute(toTemplateDataCommand, template, user);
        return ResponseEntity.ok().build();
    }
}
