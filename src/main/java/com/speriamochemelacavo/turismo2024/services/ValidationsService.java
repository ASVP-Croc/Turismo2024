package com.speriamochemelacavo.turismo2024.services;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import com.speriamochemelacavo.turismo2024.models.elements.ElementStatus;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.Contest;
import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.notifications.Notification;
import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.models.users.User;

@Service
public class ValidationsService<T extends Element> {

	@Autowired
	private LoggedUserDetailService loggedUserDetailService;
	
	@Autowired
	private NotificationsService notificationService;
	
	@Autowired
	private UsersService userService;
	
	
	public void requestValidation(T elementToValidate) {
		if (loggedUserDetailService.getLoggedUser().getRole() == Role.ROLE_AUTHORIZED_CONTRIBUTOR
				||loggedUserDetailService.getLoggedUser().getRole() == Role.ROLE_ANIMATOR
				|| loggedUserDetailService.getLoggedUser().getRole() == Role.ROLE_CURATOR
				|| loggedUserDetailService.getLoggedUser().getRole() == Role.ROLE_ADMINISTRATOR) {
			elementToValidate.setValidation(ElementStatus.APPROVED);
			notificationService.sendToSingleUser("Pubblicazione avvenuta per: " + elementToValidate.getName(), "", elementToValidate, elementToValidate.getAuthor());
		} else {
			setRecipientsOfValidation(elementToValidate);
		}
	}
	
	private void setRecipientsOfValidation(T elementToValidate) {
		List<User> recipients = new ArrayList<User>();
		
		try {
			if (elementToValidate instanceof Content
					&& (((Content)elementToValidate).getReferenced() instanceof Contest)) {
				recipients.addAll(userService.findByRole(Role.ROLE_ANIMATOR));
			} else {
				recipients.addAll(userService.findByRole(Role.ROLE_CURATOR));
			}
			sendNotifications("Hai un nuovo Elemento da validare!", elementToValidate, recipients);
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	private void sendNotifications(String message, T elementToValidate, List<User> recipients) {
		notificationService.sendToMultipleUsers("Validazione: " + elementToValidate.getName(), message, elementToValidate, recipients);
		notificationService.sendToSingleUser("Pubblicazione richiesta per: " + elementToValidate.getName(), "", elementToValidate, elementToValidate.getAuthor());
	}
	
	public void updateValidation(String message, T elementToValidate){
		notificationService.sendToSingleUser("Aggiornamento pubblicazione: " + elementToValidate.getName(), message, elementToValidate, elementToValidate.getAuthor());
	}

	public void rejectValidation(String message, T elementToValidate){
		elementToValidate.setValidation(ElementStatus.REJECTED);
		notificationService.sendToSingleUser("Pubblicazione negata: " + elementToValidate.getName(), message, elementToValidate, elementToValidate.getAuthor());
	}
	
	public void confirmValidation(T elementToValidate) {
		elementToValidate.setValidation(ElementStatus.APPROVED);
		notificationService.sendToSingleUser("Pubblicazione avvenuta: " + elementToValidate.getName(), "", elementToValidate, elementToValidate.getAuthor());
	}
}
