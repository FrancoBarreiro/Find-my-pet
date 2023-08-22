package com.findmypet.controllers;

import com.findmypet.dtos.NotificationDto;
import com.findmypet.services.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    @Autowired
    private INotificationService notificationService;

    @PostMapping
    public ResponseEntity<?> saveNotification(@RequestBody NotificationDto notification) {

        notificationService.saveNotification(notification);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(notification.getId())
                .toUri();

        return ResponseEntity.created(location).body(notification);
    }
}

