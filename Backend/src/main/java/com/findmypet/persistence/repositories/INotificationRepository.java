package com.findmypet.persistence.repositories;

import com.findmypet.persistence.entities.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INotificationRepository extends JpaRepository<Notification, Long> {
    Page<Notification> findAllByRecipient(Long userId, Pageable pageable);
}
