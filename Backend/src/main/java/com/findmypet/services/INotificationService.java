package com.findmypet.services;

import com.findmypet.dtos.NotificationDto;

public interface INotificationService {

    NotificationDto saveNotification(NotificationDto notification);
}
