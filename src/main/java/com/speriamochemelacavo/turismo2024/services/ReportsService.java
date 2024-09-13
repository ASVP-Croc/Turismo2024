package com.speriamochemelacavo.turismo2024.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.Contest;
import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.models.users.User;

@Service
public class ReportsService<T extends Element>{
	@Autowired
	NotificationsService<T> notificationService;
	
	@Autowired
	UsersService userService;
	
	public void reportElement(T elementToReport) {
		List<User> recipients = new ArrayList<User>();
		if (elementToReport instanceof Content
				&& (((Content)elementToReport).getReferenced() instanceof Contest)) {
			recipients.addAll(userService.findByRole(Role.Animator));
		} else {
			recipients.addAll(userService.findByRole(Role.Curator));
		}
		sendNotification("Seganalazione dell' elemento", elementToReport, recipients);
	}
	
	private void sendNotification(String message, T elementToValidate, List<User> recipients) {
		notificationService.sendToMultipleUsers("Segnalazione: " + elementToValidate.getName(), message, elementToValidate,recipients);
	}
	
}
