package com.example.finappapirest.notifications.interfaces;

import com.example.finappapirest.notifications.domain.model.entities.Notification;
import com.example.finappapirest.notifications.domain.model.queries.GetAllNotificationsQuery;
import com.example.finappapirest.notifications.domain.model.queries.GetNotificationsByUser;
import com.example.finappapirest.notifications.domain.services.NotificationQueryService;
import com.example.finappapirest.notifications.interfaces.rest.resources.NotificationResponse;
import com.example.finappapirest.notifications.interfaces.rest.transform.NotificationMapper;
import com.example.finappapirest.shared.interfaces.utils.UserUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/notifications")
@Tag(name = "Notifications", description = "Notifications management")
@AllArgsConstructor
public class NotificationController {

    private NotificationQueryService notificationQueryService;

    @GetMapping()
    @Operation(summary = "Get all notifications")
    public ResponseEntity<List<NotificationResponse>> getNotifications() {
        GetAllNotificationsQuery query = new GetAllNotificationsQuery();
        List<Notification> notifications = notificationQueryService.handle(query);
        List<NotificationResponse> response = notifications.stream().map(NotificationMapper::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user")
    @Operation(summary = "Get all notifications by user")
    public ResponseEntity<List<NotificationResponse>> getNotificationsByUser() {
        Long userId = UserUtils.getCurrentUserId();
        GetNotificationsByUser query = new GetNotificationsByUser(userId);
        List<Notification> notifications = notificationQueryService.handle(query);
        List<NotificationResponse> response = notifications.stream().map(NotificationMapper::toResponse).toList();
        return ResponseEntity.ok(response);
    }
}
