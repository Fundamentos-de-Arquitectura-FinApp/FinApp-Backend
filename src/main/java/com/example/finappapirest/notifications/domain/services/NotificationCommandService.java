package com.example.finappapirest.notifications.domain.services;

import com.example.finappapirest.notifications.domain.model.commands.CreateNotificationCommand;

public interface NotificationCommandService {
    void handle(CreateNotificationCommand command);
}
