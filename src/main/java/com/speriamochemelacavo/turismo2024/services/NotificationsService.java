package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.notifications.Notification;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.repository.NotificationRepository;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;

@Service
public class NotificationsService {
	
	@Autowired
	private LoggedUserDetailService loggedUserDetailService;
	
	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private UsersService userService;
	
	public Notification findById(int elemToFindId) {
		return notificationRepository.findById(elemToFindId).orElseThrow();
	}
	
	private void add(Notification notificationToAdd) {
		notificationRepository.save(notificationToAdd);
	}
	
	public <T extends Element> void sendToSingleUser(String title, String message, T object, User recipient) {
		Notification toSend = new Notification(title, message, loggedUserDetailService.getLoggedUser(), object, recipient);
		add(toSend);
//		userService.addUser(loggedUserDetailService.getLoggedUser());
	}
	
	public <T extends Element> void sendToMultipleUsers(String title, String message, T object, List<User> recipients) {
		Notification toSend = new Notification(title, message, loggedUserDetailService.getLoggedUser(), object, recipients);
		add(toSend);
//		userService.addUser(loggedUserDetailService.getLoggedUser());
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
		add(notificationUpdated);
	}
	
	public void confirmValidation(Notification notificationToConfirm) {
		Notification notificationConfirmed = notificationToConfirm;
		notificationConfirmed.setTitle("Pubblicazione avvenuta: " + notificationToConfirm.getNotificationObject().getName());
		notificationConfirmed.setMessage("");
		add(notificationConfirmed);
	}
}
