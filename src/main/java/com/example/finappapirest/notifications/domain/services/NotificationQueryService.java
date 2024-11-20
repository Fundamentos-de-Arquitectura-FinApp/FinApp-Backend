package com.example.finappapirest.notifications.domain.services;

import com.example.finappapirest.notifications.domain.model.entities.Notification;
import com.example.finappapirest.notifications.domain.model.queries.GetAllNotificationsQuery;
import com.example.finappapirest.notifications.domain.model.queries.GetNotificationsByUser;

import java.util.List;

public interface NotificationQueryService {
    List<Notification> handle(GetAllNotificationsQuery query);
    List<Notification> handle(GetNotificationsByUser query);
}
