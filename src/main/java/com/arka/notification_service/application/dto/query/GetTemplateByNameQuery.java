package com.arka.notification_service.application.dto.query;

public class GetTemplateByNameQuery {

    private final String templateName;

    public GetTemplateByNameQuery(String templateName) {
        if (templateName == null || templateName.isBlank()) {
            throw new IllegalArgumentException("Template name cannot be null or blank.");
        }
        this.templateName = templateName;
    }

    public String getTemplateName() {
        return templateName;
    }

    @Override
    public String toString() {
        return "GetTemplateByNameQuery{" +
                "templateName='" + templateName + '\'' +
                '}';
    }
}
