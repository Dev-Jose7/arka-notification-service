package com.arka.notification_service.application.exception;

public class TemplateNotFound extends RuntimeException {
    public TemplateNotFound(String message) {
        super(message);
    }

    public TemplateNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public TemplateNotFound(Throwable cause) {
        super("Template not found", cause);
    }

    public TemplateNotFound() {
        super("Template not found");
    }
}
