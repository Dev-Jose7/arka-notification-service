package com.arka.notification_service.domain.model.entity;

import com.arka.notification_service.domain.model.enums.NotificationChannel;
import com.arka.notification_service.domain.model.valueobject.Email;
import com.arka.notification_service.domain.model.valueobject.PhoneNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class User {

    private final UUID id;
    private final String name;
    private final Email email;
    private final PhoneNumber phoneNumber;
    private final boolean wantsNotifications;
    private final boolean prefersEmail;
    private final boolean prefersWhatsapp;
    private final boolean prefersSms;
    private final boolean prefersPush;

    public User(UUID id, String name, Email email, PhoneNumber phoneNumber,
                boolean wantsNotifications, boolean prefersEmail, boolean prefersWhatsapp,
                boolean prefersSms, boolean prefersPush) {

        if (id == null) throw new IllegalArgumentException("ID cannot be null.");
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be null or blank.");
        if (email == null) throw new IllegalArgumentException("Email cannot be null.");
        if (phoneNumber == null) throw new IllegalArgumentException("Phone number cannot be null.");

        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.wantsNotifications = wantsNotifications;
        this.prefersEmail = prefersEmail;
        this.prefersWhatsapp = prefersWhatsapp;
        this.prefersSms = prefersSms;
        this.prefersPush = prefersPush;
    }

    public boolean canReceiveNotifications() {
        return this.wantsNotifications && !getNotificationPreferences().isEmpty();
    }

    public boolean prefersEmailNotifications() {
        return this.prefersEmail;
    }

    public boolean prefersWhatsappNotifications() {
        return this.prefersWhatsapp;
    }

    public boolean prefersSmsNotifications() {
        return this.prefersSms;
    }

    public boolean prefersPushNotifications() {
        return this.prefersPush;
    }

    public List<NotificationChannel> getNotificationPreferences() {
        List<NotificationChannel> preferences = new ArrayList<>();

        if (this.prefersEmail) preferences.add(NotificationChannel.EMAIL);
        if (this.prefersWhatsapp) preferences.add(NotificationChannel.WHATSAPP);
        if (this.prefersSms) preferences.add(NotificationChannel.SMS);
        if (this.prefersPush) preferences.add(NotificationChannel.PUSH);

        return preferences;
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Email getEmail() {
        return this.email;
    }

    public PhoneNumber getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return this.id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", email=" + this.email +
                ", wantsNotifications=" + this.wantsNotifications +
                ", prefersEmail=" + this.prefersEmail +
                ", prefersWhatsapp=" + this.prefersWhatsapp +
                ", prefersSms=" + this.prefersSms +
                ", prefersPush=" + this.prefersPush +
                '}';
    }
}
