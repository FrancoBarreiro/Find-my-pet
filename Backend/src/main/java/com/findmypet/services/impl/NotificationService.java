package com.findmypet.services.impl;

import com.findmypet.dtos.NotificationDto;
import com.findmypet.exceptions.BadRequestException;
import com.findmypet.mappers.NotificationMapper;
import com.findmypet.persistence.entities.Notification;
import com.findmypet.persistence.repositories.INotificationRepository;
import com.findmypet.services.INotificationService;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    private INotificationRepository notificationRepository;

    private final Validator validator;

    public NotificationService(Validator validator) {
        this.validator = validator;
    }

    @Override
    public NotificationDto saveNotification(NotificationDto notification) {

        if (validator.validate(notification).isEmpty()) {
            Notification notificationToSave = NotificationMapper.dtoToEntity(notification);
            notificationRepository.save(notificationToSave);
            notification.setId(notificationToSave.getId());
            return notification;
        } else {
            throw new BadRequestException("Check the fields");
        }

    }
}
