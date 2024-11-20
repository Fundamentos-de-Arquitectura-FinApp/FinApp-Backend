package com.example.finappapirest.notifications.interfaces.rest.resources;

public record NotificationResponse(
        Long id,
        Long userId,
        String message,
        String date
) {
}
