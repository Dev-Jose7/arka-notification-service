package com.arka.notification_service.domain.model.valueobject;

import java.util.Objects;

public class PhoneNumber {

    private final String countryCode;
    private final String number;

    public PhoneNumber(String countryCode, String number) {
        if (countryCode == null || countryCode.isBlank())
            throw new IllegalArgumentException("Country code value cannot be null or blank.");

        if (!countryCode.startsWith("+"))
            throw new IllegalArgumentException("Country code must start with '+'.");

        if(number == null || number.isBlank())
            throw new IllegalArgumentException("Number value cannot be null or blank.");

        if (!number.matches("\\d+"))
            throw new IllegalArgumentException("Number phone must contain only digits.");

        this.countryCode = countryCode;
        this.number = number;
    }

    public String fullNumber() {
        return countryCode + number;
    }

    public boolean isValidE164Format() {
        return fullNumber().matches("^\\+[1-9]\\d{7,14}$");
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(countryCode, that.countryCode) && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, number);
    }

    @Override
    public String toString() {
        return fullNumber();
    }
}
