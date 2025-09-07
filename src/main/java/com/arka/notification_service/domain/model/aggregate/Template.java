package com.arka.notification_service.domain.model.aggregate;

import com.arka.notification_service.domain.model.enums.NotificationChannel;
import com.arka.notification_service.domain.model.valueobject.TemplateId;

import java.util.Map;
import java.util.Objects;

public class Template {

    private TemplateId id;
    private String name;
    private String subject;
    private String body;
    private NotificationChannel channel;

    public Template(TemplateId id, String name, String subject, String body, NotificationChannel channel) {
        if (id == null) throw new IllegalArgumentException("Template ID cannot be null");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Template name cannot be null or blank");
        if (subject == null) throw new IllegalArgumentException("Subject cannot be null");
        if (body == null) throw new IllegalArgumentException("Body cannot be null");
        if (channel == null) throw new IllegalArgumentException("Channel cannot be null");

        this.id = id;
        this.name = name;
        this.subject = subject;
        this.body = body;
        this.channel = channel;
    }

    public TemplateId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public NotificationChannel getChannel() {
        return channel;
    }

    /**
     * Render the template replacing {{placeholders}} with values from the provided map.
     */
    public String renderTemplate(Map<String, Object> variables) {
        String rendered = body;
        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            String placeholder = "{{" + entry.getKey() + "}}";
            rendered = rendered.replace(placeholder, String.valueOf(entry.getValue()));
        }
        return rendered;
    }

    /**
     * Updates subject and body content. Also updates the updatedAt timestamp.
     */
    public void updateContent(String subject, String body) {
        if (subject == null) throw new IllegalArgumentException("Subject cannot be null");
        if (body == null) throw new IllegalArgumentException("Body cannot be null");

        this.subject = subject;
        this.body = body;
    }

    // equals and hashCode based on id

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Template)) return false;
        Template template = (Template) o;
        return id.equals(template.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Template{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", channel=" + channel +
                '}';
    }
}
