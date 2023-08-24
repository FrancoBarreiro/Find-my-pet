package com.findmypet.controllers;

import com.findmypet.dtos.NotificationDto;
import com.findmypet.services.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getNotificationById(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.getNotificationById(id));
    }

    @GetMapping
    public ResponseEntity<?> getNotificationsPaged(@RequestParam Long userId, @RequestParam int size, @RequestParam int page) {
        return ResponseEntity.ok(notificationService.getNotificationsPaged(userId, size, page));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReadStatus(@PathVariable Long id) {
        notificationService.updateIsRead(id);
        return ResponseEntity.noContent().build();
    }
}

