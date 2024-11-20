package com.example.finappapirest.notifications.interfaces.acl;

import com.example.finappapirest.notifications.domain.model.commands.CreateNotificationCommand;
import com.example.finappapirest.notifications.domain.services.NotificationCommandService;
import com.example.finappapirest.notifications.infraestructure.configuration.activemq.services.MessageProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationServiceFacade {
    private NotificationCommandService notificationCommandService;

    public void sendNotification(Long userId, String message){
        CreateNotificationCommand command = new CreateNotificationCommand(userId, message);
        notificationCommandService.handle(command);
    }

}
