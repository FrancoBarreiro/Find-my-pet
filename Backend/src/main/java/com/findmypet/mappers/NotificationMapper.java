package com.findmypet.mappers;

import com.findmypet.dtos.NotificationDto;
import com.findmypet.exceptions.BadRequestException;
import com.findmypet.persistence.entities.Notification;
import com.findmypet.utils.NotificationType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotificationMapper {

    public static NotificationDto entityToDto(Notification notification) {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        return NotificationDto.builder()
                .id(notification.getId())
                .type(notification.getType().toString())
                .sender(notification.getSender())
                .recipient(notification.getRecipient())
                .date(notification.getDate().format(dateFormatter))
                .hour(notification.getDate().format(timeFormatter))
                .isRead(notification.isRead())
                .postId(notification.getPostId())
                .build();

    }

    public static Notification dtoToEntity(NotificationDto notification) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime date = LocalDateTime.parse(
                notification.getDate() + " " + notification.getHour(),
                localDateTimeFormatter);

        if (!NotificationType.isValidType(notification.getType())) {
            throw new BadRequestException("The type of notification is invalid.");
        }

        return Notification.builder()
                .id(notification.getId())
                .type(NotificationType.valueOf(notification.getType()))
                .sender(notification.getSender())
                .recipient(notification.getRecipient())
                .date(date)
                .isRead(notification.isRead())
                .postId(notification.getPostId())
                .build();
    }
}
