package com.arka.notification_service.domain.model.valueobject;

import com.arka.notification_service.domain.model.valueobject.base.IdBase;

import java.util.UUID;

public class TemplateId extends IdBase<UUID> {

    private TemplateId(UUID value) {
        super(value);
    }

    public static TemplateId generate() {
        return new TemplateId(generateValue());
    }

    public static TemplateId fromString(String id) {
        return new TemplateId(parseValue(id));
    }

    public static TemplateId fromUUID(UUID id) {
        return new TemplateId(id);
    }
}