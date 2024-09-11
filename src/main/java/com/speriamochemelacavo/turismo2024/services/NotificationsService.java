package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.notifications.Notification;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.repository.NotificationRepository;

@Service
public class NotificationsService<T extends Element> {
	
	@Autowired
	private NotificationRepository<T> notificationRepository;
	
	public Notification<T> findById(int elemToFindId) {
		return notificationRepository.findById(elemToFindId).orElseThrow();
	}
	
	public List<Notification<T>> findAllByRecipientUserId(int id){
		return notificationRepository.findAllNotificationByRecipientUsersId(id);
	}
	
	private void addNotification(Notification<T> notificationToAdd) {
		notificationRepository.save(notificationToAdd);
	}
	
	public void updateNotification(Notification<T> notificationToUpdate) {
		notificationRepository.save(notificationToUpdate);
	}
	
	public void sendToSingleUser(String title, String message, T object, User author, User recipientUser) {
		Notification<T> toSend = new Notification<T>(title, message, author, object, recipientUser);
		addNotification(toSend);
	}
	
	public void sendToMultipleUsers(String title, String message, T object, User author, List<User> recipientsUser) {
		Notification<T> toSend = new Notification<T>(title, message, author, object, recipientsUser);
		addNotification(toSend);
	}
	
	public Notification<T> readNotification(int notificationId) {
		Notification<T> toRead = findById(notificationId);
		toRead.setRead(true);
		updateNotification(toRead);
		return toRead;
	}
	
	public void deleteNotificationById(int notificationId) {
		notificationRepository.deleteById(notificationId);
	}
}
