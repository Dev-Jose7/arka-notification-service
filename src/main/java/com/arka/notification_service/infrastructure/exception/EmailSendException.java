package com.arka.notification_service.infrastructure.exception;

public class EmailSendException extends RuntimeException {

    public EmailSendException(String message) {
        super(message);
    }

    public EmailSendException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailSendException(Throwable cause) {
        super("An error occurred while sending the email.", cause);
    }

    public EmailSendException() {
        super("An error occurred while sending the email.");
    }
}
