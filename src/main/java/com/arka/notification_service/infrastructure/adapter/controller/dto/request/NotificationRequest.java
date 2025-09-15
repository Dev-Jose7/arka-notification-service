package com.arka.notification_service.infrastructure.adapter.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.UUID;

@Getter
@ToString
@AllArgsConstructor
public class NotificationRequest {

    @NotNull(message = "User ID value cannot be null.")
    private UUID userId;

    @NotBlank(message = "Template name cannot be blank.")
    private String templateName;

    @NotNull(message = "Template data cannot be null.")
    private HashMap<String, Object> templateData;
}
