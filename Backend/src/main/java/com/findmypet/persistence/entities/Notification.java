package com.findmypet.persistence.entities;

import com.findmypet.utils.NotificationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private NotificationType type;

    @Column(name = "sender", nullable = false)
    private Long sender;

    @Column(name = "recipient", nullable = false)
    private Long recipient;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "is_read", nullable = false)
    private boolean isRead;

    @Column(name = "post_id", nullable = false)
    private Long postId;

}
