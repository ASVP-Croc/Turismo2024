package com.speriamochemelacavo.turismo2024.services;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import com.speriamochemelacavo.turismo2024.models.elements.ElementStatus;
import com.speriamochemelacavo.turismo2024.models.elements.poi.POIForTour;
import com.speriamochemelacavo.turismo2024.models.notifications.Notificable;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.Contest;
import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.models.users.User;

@Service
public class ValidationsService {
	
	@Autowired
	private LoggedUserDetailService LoggedUserDetailService;
	
	@Autowired
	private NotificationsService notificationService;
	
	@Autowired
	private UsersService userService;
	
	public void requestValidation(Notificable elementToValidate) {
		
		Role userRole = LoggedUserDetailService.getLoggedUser().getRole();	
		
		if (userRole == Role.ROLE_AUTHORIZED_CONTRIBUTOR ||
				userRole == Role.ROLE_ANIMATOR ||
				userRole == Role.ROLE_CURATOR ||
				userRole == Role.ROLE_ADMINISTRATOR) {
			elementToValidate.setStatus(ElementStatus.APPROVED);
			notificationService.sendToSingleUser("Validazione richiesta per: " + elementToValidate.getName(), "", elementToValidate, elementToValidate.getAuthor());	
		} else setRecipientsOfValidation(elementToValidate);
	}
	
	private void setRecipientsOfValidation(Notificable elementToValidate) {
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
		}
	}
	
	private void sendNotifications(String message, Notificable elementToValidate, List<User> recipients) throws SQLIntegrityConstraintViolationException {
		notificationService.sendToMultipleUsers("Validazione: " + elementToValidate.getName(), message, elementToValidate, recipients);
		notificationService.sendToSingleUser("Pubblicazione richiesta per: " + elementToValidate.getName(), "", elementToValidate, elementToValidate.getAuthor());
	}
	
	public void updateValidation(String message, Notificable elementToValidate) throws SQLIntegrityConstraintViolationException{
		notificationService.sendToSingleUser("Aggiornamento pubblicazione: " + elementToValidate.getName(), message, elementToValidate, elementToValidate.getAuthor());
	}

	public void rejectValidation(String message, Notificable elementToValidate) throws SQLIntegrityConstraintViolationException{
		elementToValidate.setStatus(ElementStatus.REJECTED);
		notificationService.sendToSingleUser("Pubblicazione negata: " + elementToValidate.getName(), message, elementToValidate, elementToValidate.getAuthor());
	}
	
	public void confirmValidation(Notificable elementToValidate) throws SQLIntegrityConstraintViolationException {
		elementToValidate.setStatus(ElementStatus.APPROVED);
		notificationService.sendToSingleUser("Pubblicazione avvenuta: " + elementToValidate.getName(), "", elementToValidate, elementToValidate.getAuthor());
	}
}
