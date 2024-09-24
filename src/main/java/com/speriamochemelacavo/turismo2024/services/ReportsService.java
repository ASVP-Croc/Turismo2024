package com.speriamochemelacavo.turismo2024.services;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.content.Content;
import com.speriamochemelacavo.turismo2024.models.elements.Contest;
import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.models.users.User;

@Service
public class ReportsService<T extends Element>{
	
	@Autowired
	NotificationsService notificationService;
	
	@Autowired
	UsersService userService;
	
	public void reportElement(T elementToReport, String message) {
		List<User> recipients = new ArrayList<>();
		
		try {
			if (elementToReport instanceof Content
					&& (((Content)elementToReport).getReferenced() instanceof Contest)) {
				recipients.addAll(userService.findByRole(Role.ROLE_ANIMATOR));
			} else {
				recipients.addAll(userService.findByRole(Role.ROLE_CURATOR));
			}
			elementToReport.setReported(true);
			notificationService.sendToMultipleUsers("Segnalazione: " + elementToReport.getName(), message, elementToReport, recipients);
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
