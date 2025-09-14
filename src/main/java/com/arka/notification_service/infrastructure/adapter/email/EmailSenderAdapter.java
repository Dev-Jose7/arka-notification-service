package com.arka.notification_service.infrastructure.adapter.email;

import com.arka.notification_service.application.port.out.EmailSenderPort;
import com.arka.notification_service.domain.model.aggregate.Notification;
import com.arka.notification_service.infrastructure.exception.EmailSendException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailSenderAdapter implements EmailSenderPort {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.from}")
    private final String senderEmail;

    @Override
    public void send(Notification notification) {
        String toEmail = notification.getRecipient().getEmail().getAddress();

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(toEmail);
            helper.setSubject(notification.getRenderedSubject());
            helper.setText(notification.getRenderedBody(), true);
            helper.setFrom(senderEmail);

            mailSender.send(message);
        } catch (MessagingException ex) {
            log.error("Failed to send email for Notification ID {} to {}: {}",
                    notification.getId().getValue(), toEmail, ex.getMessage(), ex);
            throw new EmailSendException("Failed to send email to " + toEmail);
        }
    }
}
