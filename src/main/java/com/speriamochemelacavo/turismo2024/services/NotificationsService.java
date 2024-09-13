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
	
	public Notification findById(int elemToFindId) {
		return notificationRepository.findById(elemToFindId).orElseThrow();
	}
	
	public List<Notification> findAllByRecipientUserId(int id){
		return notificationRepository.findAllNotificationByRecipientUsersId(id);
	}
	
	private void addNotification(Notification notificationToAdd) {
		notificationRepository.save(notificationToAdd);
	}
	
	public void updateNotification(Notification notificationToUpdate) {
		notificationRepository.save(notificationToUpdate);
	}
	
	public void sendToSingleUser(String title, String message, T object) {
		Notification toSend = new Notification(title, message, object.getAuthor(), object, object.getAuthor());
		addNotification(toSend);
	}
	
	public void sendToMultipleUsers(String title, String message, T object, List<User> recipientsUser) {
		Notification toSend = new Notification(title, message, object.getAuthor(), object, recipientsUser);
		addNotification(toSend);
	}
	
	public Notification readNotification(int notificationId) {
		Notification toRead = findById(notificationId);
		toRead.setRead(true);
		updateNotification(toRead);
		return toRead;
	}
	
	public void deleteNotificationById(int notificationId) {
		notificationRepository.deleteById(notificationId);
	}
	
	public void updateValidation(String message, Notification notificationToResponse){
		Notification notificationUpdated = notificationToResponse;
		notificationUpdated.setTitle("Aggiornamento pubblicazione: " + notificationToResponse.getNotificationObject().getName());
		notificationUpdated.setMessage(message);
		notificationUpdated.getRecipientUsers().clear();
		notificationUpdated.getRecipientUsers().add(notificationToResponse.getNotificationObject().getAuthor());
		updateNotification(notificationUpdated);
	}
	
	public void confirmValidation(Notification notificationToConfirm) {
		Notification notificationConfirmed = notificationToConfirm;
		notificationConfirmed.setTitle("Pubblicazione avvenuta: " + notificationToConfirm.getNotificationObject().getName());
		notificationConfirmed.setMessage("");
		notificationConfirmed.getRecipientUsers().clear();
		notificationConfirmed.getRecipientUsers().add(notificationToConfirm.getNotificationObject().getAuthor());
		updateNotification(notificationConfirmed);
	}
}
