package com.backend.nexaquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.nexaquiz.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}