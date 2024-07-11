package managers;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import elements.*;
import users.*;

public class ValidationsManager {
	private final static Queue<Element> elementList = new LinkedList();
	private final static Queue<Contest> contestList = new LinkedList();

	public static boolean execute(Request request) {
		if(request.getAction()==Action.Validate && request.getUser().getRole()==Role.Curator) {
			return validateElements(request);
		} else if (request.getAction()==Action.Validate && request.getUser().getRole()==Role.Animator) {
			return validateContentInContest(request);
			} else return false;
	}
	
	private static boolean validateElements(Request request) {
			System.out.println("Benvenuto Curatore! Ci sono degli elementi da validare!");
			Element element = elementList.poll();
			if(element.getVisibility()==false)
				return validateElement(request, element);
			else if( element.getContents().anyMatch(elem->elem.getVisibility()==false))
				return validateContent(request, element);
			System.out.println("Benvenuto Curatore! Non ci sono elementi da validare al momento!");
			return false;
		}
	
	public static boolean execute(Request request, Contest contest) {
		return pendingValidation(request,contest);
	}
	
	public static boolean execute(Request request, Element element) {
		return pendingValidation(request, element);
	}
	
	private static boolean validateContentInContest(Request request) {
		while(!contestList.isEmpty()){
			System.out.println("Benvenuto Animatore! Ci sono dei Contenuti da validare!");
			Contest contest = contestList.poll();
			if(contest.getVisibility()==true)
				return validateContent(request, contest);
			} System.out.println("Benvenuto Curatore! Non ci sono elementi da validare al momento!");
			return false;
	}
	
	private static boolean pendingValidation(Request request, Contest contest) {
		return contestList.add(contest);
	}
	
	private static boolean pendingValidation(Request request, Element element) {
		return elementList.add(element);
	}
	
	private static boolean validateElement(Request request, Element element) {
		Scanner scanner = new Scanner(System.in);
		System.out.println(element);
		System.out.println("Validare L'elemento? Si/No");
		String result = scanner.nextLine();
		if(result.equals("No")) {
			return deleteMessages(request, element.getId());
		} else if(result.equals("Si")) {
			return validateMessages(request, element.getId());
		} else return false;
	}
	
	
	private static boolean validateContent(Request request, Element element) {
		Scanner scanner = new Scanner(System.in);
		Set<Content> content= element.getContents()
				.filter(elem->elem.getVisibility()==false)
				.collect(Collectors.toSet());
		if(content.iterator().hasNext()) {
			Content nextContent = content.iterator().next();
			System.out.println(nextContent);
			System.out.println("Validare il contenuto? Si/No");
			String result = scanner.nextLine();
			if(result.equals("No")) {
				return deleteMessages(request, element.getId(), nextContent.getId());
			} else if(result.equals("Si")) {
				return validateMessages(request, element.getId(), nextContent.getId());
			}
		}
		return false;
	}
			
	
	private static boolean deleteMessages(Request request, Integer id) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Inserisci una motivazione per la mancata validazione: ");
		String reason = scanner.nextLine();
		Request next = new Request(request.getUser(), Action.Delete);
		sendRequest(next, id);//notifica il Manager di eliminare l'elemento
		sendNotification(reason, next);//invia una richiesta al gestore notifiche
		return true;
	}
	
	private static boolean deleteMessages(Request request, Integer id1, Integer id2) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Inserisci una motivazione per la mancata validazione: ");
		String reason = scanner.nextLine();
		Request next = new Request(request.getUser(), Action.Delete);
		sendRequest(next, id1, id2);//notifica il Manager di eliminare l'elemento
		sendNotification(reason, next);//invia una richiesta al gestore notifiche
		return true;
	}
	
	private static boolean validateMessages(Request request, Integer id) {
		Request next = new Request(request.getUser(), Action.Post);
		sendRequest(next, id);
		sendNotification("",next);
		System.out.println("Il Contenuto è stato pubblicato ed è ora visibile sulla piattaforma!");
		return true;
	}
	
	private static boolean validateMessages(Request request, Integer id1, Integer id2) {
		Request next = new Request(request.getUser(), Action.Post);
		sendRequest(next, id1, id2);
		sendNotification("",next);
		System.out.println("Il Contenuto è stato pubblicato ed è ora visibile sulla piattaforma!");
		return true;
	}
	
	private static boolean sendNotification(String text, Request request) {
		return NotificationsManager.execute(text,request);
	}

		
//Ricontrolla. L'id inizia con 1 se è un POI, 2 se Tour.
	private static boolean sendRequest(Request request, Integer id) {
		if(id.intValue()==1) {
			return POIsManager.execute(request, id);
		} else if(id.intValue()==2) {
			return ToursManager.execute(request, id);
		} else return false;
	}
//Ricontrolla. L'id inizia con 1 se è un POI, 2 se Tour, 3 se Contest.
	private static boolean sendRequest(Request request, Integer id1, Integer id2) {
		if(id1.intValue()==1) {
			return POIsManager.execute(request, id1, id2 );
		} else if(id1.intValue()==2) {
			return ToursManager.execute(request, id1, id2);
		}  else if(id1.intValue()==3) {
			return ContestsManager.execute(request, id1, id2);
		} else return false;
	}

	private boolean checkActor(AbstractUser user) {
		return user.getRole() == Role.Contributor || user.getRole()== Role.AuthenticatedTourist;
	}
	
	}
