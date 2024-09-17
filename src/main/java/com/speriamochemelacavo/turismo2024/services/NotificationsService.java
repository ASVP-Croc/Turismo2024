package com.speriamochemelacavo.turismo2024.services;

import java.lang.System.Logger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.notifications.Notification;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.repository.NotificationRepository;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;

@Service
public class NotificationsService<T extends Element> {
	
	@Autowired
	private LoggedUserDetailService loggedUserDetailService;
	
	@Autowired
	private NotificationRepository<T> notificationRepository;
	
	public Notification findById(int elemToFindId) {
		return notificationRepository.findById(elemToFindId).orElseThrow();
	}
	
	public List<Notification> findAllByRecipientUserId(int id){
		return notificationRepository.findAllNotificationByRecipientUsersId(id);
	}
	
	private void add(Notification notificationToAdd) {
		Notification optionalNotification;
		try {
			optionalNotification = findById(notificationToAdd.getId());
			notificationToAdd.setId(optionalNotification.getId());
		} catch (Exception e) {
		}
		notificationRepository.save(notificationToAdd);
	}
	
	public void sendToSingleUser(String title, String message, T object) {
		Notification toSend = new Notification(title, message, loggedUserDetailService.getLoggedUser(), object, object.getAuthor());
		add(toSend);
	}
	
	public void sendToMultipleUsers(String title, String message, T object, List<User> recipientsUser) {
		Notification toSend = new Notification(title, message, loggedUserDetailService.getLoggedUser(), object, recipientsUser);
		add(toSend);
	}
	
	public Notification readNotification(int notificationId) {
		Notification toRead = findById(notificationId);
		toRead.setRead(true);
		add(toRead);
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
		add(notificationUpdated);
	}
	
	public void confirmValidation(Notification notificationToConfirm) {
		Notification notificationConfirmed = notificationToConfirm;
		notificationConfirmed.setTitle("Pubblicazione avvenuta: " + notificationToConfirm.getNotificationObject().getName());
		notificationConfirmed.setMessage("");
		notificationConfirmed.getRecipientUsers().clear();
		notificationConfirmed.getRecipientUsers().add(notificationToConfirm.getNotificationObject().getAuthor());
		add(notificationConfirmed);
	}
}
