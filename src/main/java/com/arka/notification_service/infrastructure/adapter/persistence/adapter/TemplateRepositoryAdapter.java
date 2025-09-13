package com.arka.notification_service.infrastructure.adapter.persistence.adapter;

import com.arka.notification_service.application.port.out.TemplateRepositoryPort;
import com.arka.notification_service.domain.model.aggregate.Template;
import com.arka.notification_service.domain.model.valueobject.TemplateId;
import com.arka.notification_service.infrastructure.adapter.persistence.entity.TemplateEntity;
import com.arka.notification_service.infrastructure.adapter.persistence.mapper.TemplatePersistenceMapper;
import com.arka.notification_service.infrastructure.adapter.persistence.repository.JpaTemplateRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class TemplateRepositoryAdapter implements TemplateRepositoryPort {

    private final JpaTemplateRepository templateRepository;

    @Override
    public Template save(Template template) {
        TemplateEntity entity = TemplatePersistenceMapper.toEntity(template);
        return TemplatePersistenceMapper.toDomain(templateRepository.save(entity));
    }

    @Override
    public Optional<Template> findById(TemplateId id) {
        return templateRepository.findById(id.getValue())
                .map(TemplatePersistenceMapper::toDomain);
    }

    @Override
    public Optional<Template> findByName(String name) {
        return templateRepository.findByName(name)
                .map(TemplatePersistenceMapper::toDomain);
    }
}
