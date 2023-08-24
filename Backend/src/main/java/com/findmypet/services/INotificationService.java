package com.findmypet.services;

import com.findmypet.dtos.NotificationDto;
import org.springframework.data.domain.Page;

public interface INotificationService {

    NotificationDto saveNotification(NotificationDto notification);

    NotificationDto getNotificationById(Long id);

    Page<NotificationDto> getNotificationsPaged(Long userdId, int size, int page);

    void deleteNotification(Long id);

    void updateIsRead(Long id);
}
