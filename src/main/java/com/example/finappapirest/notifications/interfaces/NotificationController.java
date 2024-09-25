package com.example.finappapirest.notifications.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
@Tag(name = "Notifications", description = "Notifications management")
public class NotificationController {
    @GetMapping()
    @Operation(summary = "Get all notifications")
    public String getNotifications() {
        return "Notifications";
    }

    @GetMapping("/store")
    @Operation(summary = "Get notifications by store")
    public String getNotificationsByStore() {
        return "Notifications by store";
    }
    @GetMapping("/{notificationId}")
    @Operation(summary = "Get notification by id")
    public String getNotification(@PathVariable Long notificationId) {
        return "Notification " + notificationId;
    }
    @GetMapping("/{clientId}")
    @Operation(summary = "Get notifications by client")
    public String getNotificationsByClient(@PathVariable Long clientId) {
        return "Notifications by client " + clientId;
    }
}
