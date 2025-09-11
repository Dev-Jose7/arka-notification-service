package com.arka.notification_service.infrastructure.adapter.persistence.entity;

import com.arka.notification_service.domain.model.enums.NotificationState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "notification_history")
public class NotificationHistoryEntity {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "notification_id", nullable = false)
    private NotificationEntity notification;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private NotificationState state;

    @Column(name = "attempts_number", nullable = false)
    private Integer attemptsNumber;

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
