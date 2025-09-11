package com.arka.notification_service.application.port.out;

import com.arka.notification_service.domain.model.aggregate.Template;
import com.arka.notification_service.domain.model.valueobject.TemplateId;

import java.util.Optional;

public interface TemplateRepositoryPort {
    Template save(Template template);
    Optional<Template> findById(TemplateId id);
    Optional<Template> findByName(String name);
}
