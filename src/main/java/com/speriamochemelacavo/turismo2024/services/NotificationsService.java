package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.notifications.Notification;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.repository.NotificationRepository;

@Service
public class NotificationsService {
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	public void sendToSingleUser(String title, String message, Element object, User recipientUser) {
		Notification toSend = new Notification(title, message, object);
		toSend.getRecipientUser().add(recipientUser);
		notificationRepository.save(toSend);
	}
	
	public void sendToMultipleUsers(String title, String message, Element object, List<User> recipientUser) {
		Notification toSend = new Notification(title, message, object);
		toSend.getRecipientUser().addAll(recipientUser);
		notificationRepository.save(toSend);
	}
	
	public Notification readNotification(int notificationId) {
		Notification toRead = notificationRepository.findById(notificationId).orElseThrow();
		toRead.setRead(true);
		return toRead;
	}
	
	public void deleteNotification(int notificationId) {
		notificationRepository.deleteById(notificationId);
	}
}
