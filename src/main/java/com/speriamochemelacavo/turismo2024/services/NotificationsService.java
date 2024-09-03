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
	
	public Notification findById(int elemToFindId) {
		return notificationRepository.findById(elemToFindId).orElseThrow();
	}
	
	public List<Notification> findAll(){
		return notificationRepository.findAll();
	}
	
	public void addNotification(Notification notificationToAdd) {
		notificationRepository.save(notificationToAdd);
	}
	
	public void addNotifications(List<Notification> notificationsToAdd) {
		notificationsToAdd.stream().forEach(e -> addNotification(e));
	}
	
	public void updateNotification(Notification notificationToUpdate) {
		notificationRepository.save(notificationToUpdate);
	}
	
	public void deleteElement(Notification notificationToDelete) {
		notificationRepository.delete(notificationToDelete);
	}
	
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
