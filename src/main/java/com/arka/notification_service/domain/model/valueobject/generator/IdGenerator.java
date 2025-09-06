package com.arka.notification_service.domain.model.valueobject.generator;

public abstract class IdGenerator<T> {
    public abstract T generate();
    public abstract T parse(String id);
}
