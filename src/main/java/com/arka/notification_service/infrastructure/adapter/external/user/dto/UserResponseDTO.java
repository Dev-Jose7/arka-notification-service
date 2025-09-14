package com.arka.notification_service.infrastructure.adapter.external.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserResponseDTO {

    private String id;
    private String name;
    private String email;

    @JsonProperty("country_code")
    private String countryCode;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("wants_notifications")
    private boolean wantsNotifications;

    @JsonProperty("prefers_email")
    private boolean prefersEmail;

    @JsonProperty("prefers_whatsapp")
    private boolean prefersWhatsapp;

    @JsonProperty("prefers_sms")
    private boolean prefersSms;

    @JsonProperty("prefers_push")
    private boolean prefersPush;
}
