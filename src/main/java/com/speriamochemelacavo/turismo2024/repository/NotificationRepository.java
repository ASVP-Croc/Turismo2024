package com.speriamochemelacavo.turismo2024.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.notifications.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer>{
	
}
