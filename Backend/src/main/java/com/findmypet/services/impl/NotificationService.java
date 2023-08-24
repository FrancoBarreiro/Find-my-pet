package com.findmypet.services.impl;

import com.findmypet.dtos.NotificationDto;
import com.findmypet.exceptions.BadRequestException;
import com.findmypet.exceptions.ResourceNotFoundException;
import com.findmypet.mappers.NotificationMapper;
import com.findmypet.persistence.entities.Notification;
import com.findmypet.persistence.entities.User;
import com.findmypet.persistence.repositories.INotificationRepository;
import com.findmypet.persistence.repositories.IUserRepository;
import com.findmypet.services.INotificationService;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    private INotificationRepository notificationRepository;

    @Autowired
    private IUserRepository userRepository;

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

    private String getSenderUsername(Long senderId) {
        User sender = userRepository.findById(senderId).orElse(null);
        return sender != null ? sender.getUsername() : "Unknown Sender";
    }

    @Override
    public NotificationDto getNotificationById(Long id) {
        Optional<Notification> notificationFound = notificationRepository.findById(id);

        if (notificationFound.isPresent()) {
            NotificationDto notification = NotificationMapper.entityToDto(notificationFound.get());
            notification.setSenderUsername(getSenderUsername(notificationFound.get().getSender()));
            return notification;
        } else {
            throw new ResourceNotFoundException("Notification not found");
        }
    }

    @Override
    public Page<NotificationDto> getNotificationsPaged(Long userId, int size, int page) {
        Sort sort = Sort.by("date").ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Notification> notificationsFound = notificationRepository.findAllByRecipient(userId, pageable);
        List<NotificationDto> notifications = new ArrayList<>();

        for (Notification noti : notificationsFound) {
            NotificationDto notification = NotificationMapper.entityToDto(noti);
            notification.setSenderUsername(getSenderUsername(noti.getSender()));
            notifications.add(notification);
        }
        return new PageImpl<>(notifications, notificationsFound.getPageable(), notificationsFound.getTotalElements());
    }

    @Override
    public void deleteNotification(Long id) {
        Optional<Notification> notificationFound = notificationRepository.findById(id);

        if (notificationFound.isPresent()) {
            notificationRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Notification not found");
        }
    }

    @Override
    public void updateIsRead(Long id) {
        Optional<Notification> notificationFound = notificationRepository.findById(id);

        if (notificationFound.isPresent()) {
            Notification notification = notificationFound.get();
            notification.setRead(true);
            notificationRepository.save(notification);
        } else {
            throw new ResourceNotFoundException("Notification not found");
        }
    }
}
