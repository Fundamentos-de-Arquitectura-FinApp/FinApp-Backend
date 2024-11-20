package com.example.finappapirest.notifications.infraestructure.persistence.jpa.repositories;

import com.example.finappapirest.notifications.domain.model.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findByUserId(Long user_id);
}
