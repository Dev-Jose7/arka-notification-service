package com.arka.notification_service.application.dto.command;

import java.util.Map;

public class TemplateDataCommand {

    private final Map<String, Object> templateData;

    public TemplateDataCommand(Map<String, Object> templateData) {
        if (templateData == null || templateData.isEmpty())
            throw new IllegalArgumentException("Template data cannot be null or empty.");

        this.templateData = Map.copyOf(templateData);
    }

    public Map<String, Object> getTemplateData() {
        return templateData;
    }

    @Override
    public String toString() {
        return "TemplateDataCommand{" +
                "templateData=" + templateData +
                '}';
    }
}
