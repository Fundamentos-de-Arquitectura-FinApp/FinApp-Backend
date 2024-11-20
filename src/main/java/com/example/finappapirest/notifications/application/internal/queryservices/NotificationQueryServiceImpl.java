package com.example.finappapirest.notifications.application.internal.queryservices;

import com.example.finappapirest.notifications.domain.model.entities.Notification;
import com.example.finappapirest.notifications.domain.model.queries.GetAllNotificationsQuery;
import com.example.finappapirest.notifications.domain.model.queries.GetNotificationsByUser;
import com.example.finappapirest.notifications.domain.services.NotificationQueryService;
import com.example.finappapirest.notifications.infraestructure.persistence.jpa.repositories.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationQueryServiceImpl implements NotificationQueryService {
    private NotificationRepository notificationRepository;

    @Override
    public List<Notification> handle(GetAllNotificationsQuery query) {
        return notificationRepository.findAll();
    }

    @Override
    public List<Notification> handle(GetNotificationsByUser query) {
        return notificationRepository.findByUserId(query.user_id());
    }
}
