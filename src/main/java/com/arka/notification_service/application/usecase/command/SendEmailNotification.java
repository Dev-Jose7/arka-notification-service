package com.arka.notification_service.application.usecase.command;

import com.arka.notification_service.application.dto.command.TemplateDataCommand;
import com.arka.notification_service.application.port.in.SendEmailNotificationUseCase;
import com.arka.notification_service.application.port.out.DomainEventDispatcherPort;
import com.arka.notification_service.application.port.out.EmailSenderPort;
import com.arka.notification_service.application.port.out.NotificationRepositoryPort;
import com.arka.notification_service.application.port.out.TemplateRepositoryPort;
import com.arka.notification_service.domain.model.aggregate.Notification;
import com.arka.notification_service.domain.model.aggregate.Template;
import com.arka.notification_service.domain.model.entity.User;
import com.arka.notification_service.domain.model.valueobject.NotificationId;

public class SendEmailNotification implements SendEmailNotificationUseCase {

    private final NotificationRepositoryPort notificationRepository;
    private final TemplateRepositoryPort templateRepository;
    private final EmailSenderPort emailSender;
    private final DomainEventDispatcherPort domainEventDispatcher;

    public SendEmailNotification(NotificationRepositoryPort notificationRepository,
                                 TemplateRepositoryPort templateRepository,
                                 EmailSenderPort emailSender,
                                 DomainEventDispatcherPort domainEventDispatcher) {
        this.notificationRepository = notificationRepository;
        this.templateRepository = templateRepository;
        this.emailSender = emailSender;
        this.domainEventDispatcher = domainEventDispatcher;
    }

    @Override
    public void execute(TemplateDataCommand command, Template template, User user) {
        String renderedBody = template.renderTemplate(command.getTemplateData());
        String renderedSubject = template.getSubject();

        NotificationId notificationId = NotificationId.generate();
        Notification notification = new Notification(
                notificationId,
                template.getId(),
                user,
                renderedSubject,
                renderedBody,
                template.getChannel()
        );

        try {
            emailSender.send(notification);
            notification.markAsSent();
        } catch (Exception ex) {
            notification.markAsFailed(ex.getMessage());
        }

        notification.extractEvents()
                        .forEach(domainEventDispatcher::dispatch);

        notificationRepository.save(notification);
    }
}
