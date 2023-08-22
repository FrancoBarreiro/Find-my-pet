package com.findmypet.persistence.repositories;

import com.findmypet.persistence.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INotificationRepository extends JpaRepository<Notification, Long> {
}
