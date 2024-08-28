package com.speriamochemelacavo.turismo2024.services;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.*;
import com.speriamochemelacavo.turismo2024.models.elements.Contest;
import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.models.elements.Tour;
import com.speriamochemelacavo.turismo2024.models.users.GeneralUser;
import com.speriamochemelacavo.turismo2024.models.users.Role;

@Service
public class ValidationsService {
//	private final static Queue<Element> elementList = new LinkedList();
//	private final static Queue<Contest> contestList = new LinkedList();
//
//	public static boolean execute(Request request) {
//		if(request.getAction()==Action.Validate && request.getUser().getRole()==Role.Curator) {
//			return validateElements(request);
//		} else if (request.getAction()==Action.Validate && request.getUser().getRole()==Role.Animator) {
//			return validateContentsInContest(request);
//			} else return false;
//	}
//	
//	private static boolean validateElements(Request request) {
//		if(elementList.isEmpty()) {System.out.println("Benvenuto Curatore! Non ci sono elementi da validare al momento!");
//		return true;
//		} else { Scanner scanner = new Scanner(System.in);
//			System.out.println("Benvenuto Curatore! Ci sono degli elementi da validare!");
//			Integer select = 1;
//			while(!elementList.isEmpty() && select==1) {
//				Element element = elementList.poll();
//			if(element.isPublished()==false)
//				validateElement(request, element);
//			else if(element.getContents().anyMatch(content->content.isPublished()==false))
//				validateContent(request, element);
//			System.out.println("1-Visualizza il prossimo elemento da validare, 2-Esci");
//			select=scanner.nextInt();}
//		} return true;
//	}
//	
//	private static boolean validateContentsInContest(Request request) {
//		if(contestList.isEmpty()){ System.out.println("Benvenuto Curatore! Non ci sono elementi da validare al momento!");
//		return true;
//		} else { Scanner scanner = new Scanner(System.in);
//			System.out.println("Benvenuto Animatore! Ci sono dei Contenuti da validare!");
//			Integer select = 1;
//			while(!contestList.isEmpty() && select==1) {
//				Contest contest = contestList.poll();
//			if(contest.getContents().anyMatch(content->content.isPublished()==false))
//				validateContent(request, contest);
//			System.out.println("1-Visualizza il prossimo Contest per validare i contenuti, 2-Esci");
//			select=scanner.nextInt();
//		} return false;
//		}
//	}
//	
//	private static boolean autoValidation(Request request, Element element) {
//		if(request.getAction()==Action.CreatePOI || request.getAction()==Action.CreateTour || request.getAction()==Action.AddPOIInTour)
//		if(element.isPublished()==false) {
//			element.setPublished(true);;
//			return true;
//			}
//		else if(request.getAction()==Action.CreateContentInContest || request.getAction()==Action.CreateContentInPOI || request.getAction()==Action.CreateContentInTour) {
//			element.getContents().filter(elem->elem.getCreator().getRole()==Role.AuthorizedContributor || elem.getCreator().getRole()==Role.Curator)
//			.forEach(elem->elem.setPublished(true));
//			return true;
//		} return false;
//	}
//	
//	private static boolean validateElement(Request request, Element element) {
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Dsc: "+ element.getDescription()+" "+ "Id: "+ element.getId());
//		System.out.println("1-per validare L'elemento, 2-per scartere l'elemento");
//		Integer result = scanner.nextInt();
//		if(result==2) {
//			while(elementList.contains(element)) {
//				elementList.remove(element);
//			}
//			return deleteMessages(request, element);
//		} else if(result==1) {
//			return validateMessages(request, element);
//		} else return false;
//	}
//	
//	
//	private static boolean validateContent(Request request, Element element) {
//		Scanner scanner = new Scanner(System.in);
//		element.getContents().filter(content->content.isPublished()==false)
//				.forEach(content->System.out.println("ID: "+content.getId()));
//		System.out.println("Inserisci l'ID per visualizzare il Contenuto");
//		Integer id = scanner.nextInt();
//		System.out.println("Dsc: "+element.getContent(id).getText());
//		System.out.println("1-per validare il Contenuto, 2-per scartare il Contenuto");
//		Integer result = scanner.nextInt();
//		if(result==2) {
//			return deleteMessages(request, element, id);
//		} else if(result==1) {
//			return validateMessages(request, element,id);
//		} return false;
//	}
//			
//	
//	private static boolean deleteMessages(Request request, Element element) {
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Inserisci una motivazione per la mancata validazione: ");
//		String reason = scanner.nextLine();
//		Request next = new Request(request.getUser(), Action.Delete);
//		sendRequest(next, element);//notifica il Manager di eliminare l'elemento
//		sendNotification(next, reason, element.getAuthorId());//invia una richiesta al gestore notifiche
//		return true;
//	}
//	
//	private static boolean deleteMessages(Request request, Element element, Integer id2) {
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Inserisci una motivazione per la mancata validazione: ");
//		String reason = scanner.nextLine();
//		Request next = new Request(request.getUser(), Action.Delete);
//		sendRequest(next, element, id2);//notifica il Manager di eliminare l'elemento
//		sendNotification(next, reason, element.getAuthorId());//invia una richiesta al gestore notifiche
//		return true;
//	}
//	
//	private static boolean validateMessages(Request request, Element element) {
//		Request next = new Request(request.getUser(), Action.Post);
//		sendRequest(next, element);
//		sendNotification(next, " ", element.getAuthorId());
//		System.out.println("Il Contenuto è stato pubblicato ed è ora visibile sulla piattaforma!");
//		return true;
//	}
//	
//	private static boolean validateMessages(Request request, Element element, Integer id2) {
//		Request next = new Request(request.getUser(), Action.Post);
//		sendRequest(next, element, id2);
//		sendNotification(next, " ", element.getAuthorId());
//		System.out.println("Il Contenuto è stato pubblicato ed è ora visibile sulla piattaforma!");
//		return true;
//	}
//	
//	private static boolean sendNotification(Request request, String text, Integer id) {
//		return NotificationsManager.execute(request, text, id);
//	}
//
//	
//	private static boolean sendRequest(Request request, Element element) {
//		if(element instanceof PointOfInterest) {
//			return POIsManager.execute(request, element.getId());
//		} else if(element instanceof Tour) {
//			return ToursManager.execute(request, element.getId());
//		} else return false;
//	}
//	
//	private static boolean sendRequest(Request request, Element element, Integer id2) {
//		if(element instanceof PointOfInterest) {
//			return POIsManager.execute(request, element.getId(), id2 );
//		} else if(element instanceof Tour) {
//			return ToursManager.execute(request, element.getId(), id2);
//		}  else if(element instanceof Contest) {
//			return ContestsManager.execute(request, element.getId(), id2);
//		} else return false;
//	}
//
//	private static boolean checkActor(GeneralUser user) {
//		return user.getRole() == Role.Contributor || user.getRole()== Role.AuthenticatedTourist;
//	}
//	
//	public static boolean execute(Request request, Contest contest) {
//		return pendingValidation(request,contest);
//	}
//	
//	public static boolean execute(Request request, Element element) {
//		return pendingValidation(request, element);
//	}
//	
//	private static boolean pendingValidation(Request request, Contest contest) {
//		if(checkActor(request.getUser())==true) {
//			sendNotificationToValidators(request, "Salve Animatore! Hai un nuovo contenuto da validare!");
//		return contestList.add(contest);
//		} else return autoValidation(request, contest);
//	}
//	
//	private static boolean pendingValidation(Request request, Element element) {
//		if(checkActor(request.getUser())==true) {
//			sendNotificationToValidators(request, "Salve Curatore! Hai un nuovo elemento da validare!");
//		return elementList.add(element);
//		} else return autoValidation(request, element);
//	}
//	
//	private static boolean sendNotificationToValidators(Request request, String text) {
//		return NotificationsManager.execute(request, text);
//	}
	
	
	}
