package com.arka.notification_service.domain.model.valueobject.base;

import com.arka.notification_service.domain.model.valueobject.generator.IdGenerator;
import com.arka.notification_service.domain.model.valueobject.generator.UUIDGenerator;

import java.util.Objects;

public abstract class IdBase<T> {

    private static final IdGenerator<?> GENERATOR = new UUIDGenerator(); // Cambiar aquí si es necesario

    protected final T value;

    protected IdBase(T value) {
        if (value == null) throw new IllegalArgumentException("ID value cannot be null.");
        this.value = value;
    }

    @SuppressWarnings("unchecked")
    protected static <T> T generateValue() {
        return (T) GENERATOR.generate();
    }

    @SuppressWarnings("unchecked")
    protected static <T> T parseValue(String id) {
        return (T) GENERATOR.parse(id);
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdBase<?> idBase = (IdBase<?>) o;
        return Objects.equals(value, idBase.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
