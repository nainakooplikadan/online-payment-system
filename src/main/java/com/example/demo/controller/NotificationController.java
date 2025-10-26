package com.example.demo.controller;

import com.example.demo.model.Notification;
import com.example.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Get notifications for a user
    @GetMapping("/{userId}")
    public List<Notification> getNotifications(@PathVariable Long userId) {
        return notificationService.getNotifications(userId);
    }

    // Add notification (can be called internally after a transfer or payment)
    @PostMapping("/add")
    public Notification addNotification(@RequestParam Long userId, @RequestParam String message) {
        return notificationService.addNotification(userId, message);
    }
}
