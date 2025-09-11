package com.arka.notification_service.infrastructure.adapter.persistence.repository;

import com.arka.notification_service.infrastructure.adapter.persistence.entity.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaTemplateRepository extends JpaRepository<TemplateEntity, UUID> {
    Optional<TemplateEntity> findByName(String name);
}
