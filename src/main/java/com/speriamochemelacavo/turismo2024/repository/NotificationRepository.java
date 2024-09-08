package com.speriamochemelacavo.turismo2024.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.speriamochemelacavo.turismo2024.models.notifications.Notification;
import com.speriamochemelacavo.turismo2024.models.users.User;

public interface NotificationRepository extends JpaRepository<Notification, Integer>{
	
	List<Notification> findAllNotificationByRecipientUsersId(int id);
	
}
