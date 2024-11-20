package com.example.finappapirest.notifications.application.internal.commandservices;

import com.example.finappapirest.notifications.domain.model.commands.CreateNotificationCommand;
import com.example.finappapirest.notifications.domain.model.entities.Notification;
import com.example.finappapirest.notifications.domain.services.NotificationCommandService;
import com.example.finappapirest.notifications.infraestructure.configuration.activemq.services.MessageProducer;
import com.example.finappapirest.notifications.infraestructure.persistence.jpa.repositories.NotificationRepository;
import com.example.finappapirest.notifications.interfaces.rest.resources.NotificationResponse;
import com.example.finappapirest.notifications.interfaces.rest.transform.NotificationMapper;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationCommandServiceImpl implements NotificationCommandService {
    private NotificationRepository notificationRepository;
    private MessageProducer messageProducer;
    private final Gson gson;

    @Override
    public void handle(CreateNotificationCommand command) {
        Notification notification = new Notification(null,command.user_id(), command.message(), LocalDateTime.now());
        notificationRepository.save(notification);
        NotificationResponse response = NotificationMapper.toResponse(notification);
        messageProducer.sendMessage(command.user_id().toString(), gson.toJson(response));
    }
}
