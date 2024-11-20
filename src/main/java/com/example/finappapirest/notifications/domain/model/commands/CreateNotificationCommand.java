package com.example.finappapirest.notifications.domain.model.commands;

public record CreateNotificationCommand(
        Long user_id,
        String message
) {
}
