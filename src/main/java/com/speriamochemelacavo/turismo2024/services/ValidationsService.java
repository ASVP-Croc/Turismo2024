package com.speriamochemelacavo.turismo2024.services;

import java.util.ArrayList;
import java.util.List;

import com.speriamochemelacavo.turismo2024.models.notifications.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.Contest;
import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.models.users.User;

@Service
public class ValidationsService<T extends Element> {
	
	@Autowired
	private NotificationsService<T> notificationService;
	
	@Autowired
	private UsersService userService;
	
	
	public boolean requestValidation(T elementToValidate) {
		if (elementToValidate.getAuthor().getRole() == Role.AuthorizedContributor 
				|| elementToValidate.getAuthor().getRole() == Role.Curator
				|| elementToValidate.getAuthor().getRole() == Role.Administrator) {
			elementToValidate.setPublished(true);
			notificationService.sendToSingleUser("Pubblicazione avvenuta per: " + elementToValidate.getName(), "", elementToValidate);
			return true;
		} else {
			setValidation(elementToValidate);
			return false;
		}
	}
	
	private void setValidation(T elementToValidate) {
		List<User> recipients = new ArrayList<User>();
		if (elementToValidate instanceof Content
				&& (((Content)elementToValidate).getReferenced() instanceof Contest)) {
			recipients.addAll(userService.findByRole(Role.Animator));
		} else {
			recipients.addAll(userService.findByRole(Role.Curator));
		}
		sendNotification("Hai un nuovo Elemento da validare!", elementToValidate, recipients);
	}
	
	private void sendNotification(String message, T elementToValidate, List<User> recipients) {
		notificationService.sendToMultipleUsers("Validazione: " + elementToValidate.getName(), message, elementToValidate, recipients);
		notificationService.sendToSingleUser("Pubblicazione richiesta per: " + elementToValidate.getName(), "", elementToValidate);
	}
}
