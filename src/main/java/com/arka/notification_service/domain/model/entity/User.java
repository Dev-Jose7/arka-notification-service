package com.arka.notification_service.domain.model.entity;

import com.arka.notification_service.domain.model.valueobject.Email;

import java.util.Objects;
import java.util.UUID;

public class User {

    private final UUID id;
    private final String name;
    private final Email email;
    private final boolean wantsNotifications;
    private final boolean prefersEmail;
    private final boolean prefersWhatsapp;
    private final boolean prefersSms;
    private final boolean prefersPush;

    public User(UUID id, String name, Email email, boolean wantsNotifications,
                boolean prefersEmail, boolean prefersWhatsapp, boolean prefersSms, boolean prefersPush) {

        if (id == null) throw new IllegalArgumentException("ID cannot be null.");
        if (name == null) throw new IllegalArgumentException("Name cannot be null.");
        if (email == null) throw new IllegalArgumentException("Email cannot be null.");

        this.id = id;
        this.name = name;
        this.email = email;
        this.wantsNotifications = wantsNotifications;
        this.prefersEmail = prefersEmail;
        this.prefersWhatsapp = prefersWhatsapp;
        this.prefersSms = prefersSms;
        this.prefersPush = prefersPush;
    }

    public boolean canReceiveNotifications() {
        return this.wantsNotifications;
    }

    public boolean prefersEmailNotifications() {
        return prefersEmail;
    }

    public boolean prefersWhatsappNotifications() {
        return prefersWhatsapp;
    }

    public boolean prefersSmsNotifications() {
        return prefersSms;
    }

    public boolean prefersPushNotifications() {
        return prefersPush;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email=" + email +
                ", wantsNotifications=" + wantsNotifications +
                ", prefersEmail=" + prefersEmail +
                ", prefersWhatsapp=" + prefersWhatsapp +
                ", prefersSms=" + prefersSms +
                ", prefersPush=" + prefersPush +
                '}';
    }
}
