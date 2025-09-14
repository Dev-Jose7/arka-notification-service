package com.arka.notification_service.application.usecase.query;

import com.arka.notification_service.application.dto.query.GetTemplateByNameQuery;
import com.arka.notification_service.application.port.in.GetTemplateByNameUseCase;
import com.arka.notification_service.application.port.out.TemplateRepositoryPort;
import com.arka.notification_service.domain.model.aggregate.Template;


public class GetTemplateByName implements GetTemplateByNameUseCase {

    private TemplateRepositoryPort templateRepository;

    public GetTemplateByName(TemplateRepositoryPort templateRepository) {
        this.templateRepository = templateRepository;
    }

    @Override
    public Template execute(GetTemplateByNameQuery query) {
        return templateRepository.findByName(query.getTemplateName())
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
    }
}
