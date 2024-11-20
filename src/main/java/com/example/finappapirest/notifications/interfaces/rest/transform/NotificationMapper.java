package com.example.finappapirest.notifications.interfaces.rest.transform;

import com.example.finappapirest.notifications.domain.model.commands.CreateNotificationCommand;
import com.example.finappapirest.notifications.domain.model.entities.Notification;
import com.example.finappapirest.notifications.interfaces.rest.resources.CreateNotificationRequest;
import com.example.finappapirest.notifications.interfaces.rest.resources.NotificationResponse;

public class NotificationMapper {
    public static CreateNotificationCommand requestToCommand (CreateNotificationRequest request, Long userId){
        return new CreateNotificationCommand(userId, request.message());
    }

    public static NotificationResponse toResponse(Notification notification){
        return new NotificationResponse(
                notification.getId(),
                notification.getUserId(),
                notification.getMessage(),
                notification.getDate().toString()
        );
    }
}
