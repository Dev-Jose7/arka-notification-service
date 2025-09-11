package com.arka.notification_service.infrastructure.adapter.persistence.mapper;

import com.arka.notification_service.domain.model.aggregate.Template;
import com.arka.notification_service.domain.model.valueobject.TemplateId;
import com.arka.notification_service.infrastructure.adapter.persistence.entity.TemplateEntity;

public class TemplatePersistenceMapper {

    public static Template toDomain(TemplateEntity entity) {
        return new Template(
                TemplateId.fromUUID(entity.getId()),
                entity.getName(),
                entity.getSubject(),
                entity.getBody(),
                entity.getChannel()
        );
    }

    public static TemplateEntity toEntity(Template domain) {
        TemplateEntity entity =  new TemplateEntity();
        entity.setId(domain.getId().getValue());
        entity.setName(domain.getName());
        entity.setChannel(domain.getChannel());
        entity.setSubject(domain.getSubject());
        entity.setBody(domain.getBody());

        return entity;

    }
}
