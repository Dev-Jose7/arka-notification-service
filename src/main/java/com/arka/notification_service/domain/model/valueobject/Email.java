package com.arka.notification_service.domain.model.valueobject;

import lombok.Getter;

import java.util.Objects;
import java.util.regex.Pattern;

public class Email {

    @Getter
    private final String address;

    private final static Pattern EMAIL_REGEX =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public Email(String address) {

        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }

        if (!EMAIL_REGEX.matcher(address).matches()) {
            throw new IllegalArgumentException("Invalid email format: " + address);
        }

        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return address.equalsIgnoreCase(email.address);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(address.toLowerCase());
    }

    @Override
    public String toString() {
        return "Email{" +
                "address='" + address + '\'' +
                '}';
    }
}
