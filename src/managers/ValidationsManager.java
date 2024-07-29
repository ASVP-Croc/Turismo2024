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
			return validateContentsInContest(request);
			} else return false;
	}
	
	private static boolean validateElements(Request request) {
		if(elementList.isEmpty()) {System.out.println("Benvenuto Curatore! Non ci sono elementi da validare al momento!");
		return true;
		} else { Scanner scanner = new Scanner(System.in);
			System.out.println("Benvenuto Curatore! Ci sono degli elementi da validare!");
			Integer select = 1;
			while(!elementList.isEmpty() && select==1) {
				Element element = elementList.poll();
			if(element.getVisibility()==false)
				validateElement(request, element);
			else if(element.getContents().anyMatch(content->content.getVisibility()==false))
				validateContent(request, element);
			System.out.println("1-Visualizza il prossimo elemento da validare, 2-Esci");
			select=scanner.nextInt();}
		} return true;
	}
	
	private static boolean validateContentsInContest(Request request) {
		if(contestList.isEmpty()){ System.out.println("Benvenuto Curatore! Non ci sono elementi da validare al momento!");
		return true;
		} else { Scanner scanner = new Scanner(System.in);
			System.out.println("Benvenuto Animatore! Ci sono dei Contenuti da validare!");
			Contest contest = contestList.poll();
			Integer select = 1;
			while(contest!=null && select==1) {
			if(contest.getContents().anyMatch(content->content.getVisibility()==false))
				validateContent(request, contest);
			System.out.println("1-Visualizza il prossimo Contest per validare i contenuti, 2-Esci");
			select=scanner.nextInt();
			if(select==1) contest = contestList.poll();
		} return false;
		}
	}
	
	public static boolean execute(Request request, Contest contest) {
		return pendingValidation(request,contest);
	}
	
	public static boolean execute(Request request, Element element) {
		return pendingValidation(request, element);
	}
	
	private static boolean autoValidation(Request request, Element element) {
		if(request.getAction()==Action.CreatePOI || request.getAction()==Action.CreateTour || request.getAction()==Action.AddPOIInTour)
		if(element.getVisibility()==false) {
			element.setVisibility();
			return true;
			}
		else if(request.getAction()==Action.CreateContentInContest || request.getAction()==Action.CreateContentInPOI || request.getAction()==Action.CreateContentInTour) {
			element.getContents().filter(elem->elem.getCreator()==Role.AuthorizedContributor || elem.getCreator()==Role.Curator)
			.forEach(elem->elem.setVisibility());
			return true;
		} return false;
	}
	
	private static boolean pendingValidation(Request request, Contest contest) {
		if(checkActor(request.getUser())==true) {
		return contestList.add(contest);
		} else return autoValidation(request, contest);
	}
	
	private static boolean pendingValidation(Request request, Element element) {
		if(checkActor(request.getUser())==true) {
		return elementList.add(element);
		} else return autoValidation(request, element);
	}
	
	private static boolean validateElement(Request request, Element element) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Dsc: "+ element.getDescritpion()+" "+ "Id: "+ element.getId());
		System.out.println("1-per validare L'elemento, 2-per scartere l'elemento");
		Integer result = scanner.nextInt();
		if(result==2) {
			while(elementList.contains(element)) {
				elementList.remove(element);
			}
			return deleteMessages(request, element);
		} else if(result==1) {
			return validateMessages(request, element);
		} else return false;
	}
	
	
	private static boolean validateContent(Request request, Element element) {
		Scanner scanner = new Scanner(System.in);
		element.getContents().filter(content->content.getVisibility()==false)
				.forEach(content->System.out.println("ID: "+content.getId()));
		System.out.println("Inserisci l'ID per visualizzare il Contenuto");
		Integer id = scanner.nextInt();
		System.out.println("Dsc: "+element.getContent(id).getText());
		System.out.println("1-per validare il Contenuto, 2-per scartare il Contenuto");
		Integer result = scanner.nextInt();
		if(result==2) {
			return deleteMessages(request, element, id);
		} else if(result==1) {
			return validateMessages(request, element,id);
		} return false;
	}
			
	
	private static boolean deleteMessages(Request request, Element element) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Inserisci una motivazione per la mancata validazione: ");
		String reason = scanner.nextLine();
		Request next = new Request(request.getUser(), Action.Delete);
		sendRequest(next, element);//notifica il Manager di eliminare l'elemento
		sendNotification(reason, next);//invia una richiesta al gestore notifiche
		return true;
	}
	
	private static boolean deleteMessages(Request request, Element element, Integer id2) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Inserisci una motivazione per la mancata validazione: ");
		String reason = scanner.nextLine();
		Request next = new Request(request.getUser(), Action.Delete);
		sendRequest(next, element, id2);//notifica il Manager di eliminare l'elemento
		sendNotification(reason, next);//invia una richiesta al gestore notifiche
		return true;
	}
	
	private static boolean validateMessages(Request request, Element element) {
		Request next = new Request(request.getUser(), Action.Post);
		sendRequest(next, element);
		sendNotification("",next);
		System.out.println("Il Contenuto è stato pubblicato ed è ora visibile sulla piattaforma!");
		return true;
	}
	
	private static boolean validateMessages(Request request, Element element, Integer id2) {
		Request next = new Request(request.getUser(), Action.Post);
		sendRequest(next, element, id2);
		sendNotification("",next);
		System.out.println("Il Contenuto è stato pubblicato ed è ora visibile sulla piattaforma!");
		return true;
	}
	
	private static boolean sendNotification(String text, Request request) {
		return NotificationsManager.execute(text,request);
	}

	
	private static boolean sendRequest(Request request, Element element) {
		if(element instanceof PointOfInterest) {
			return POIsManager.execute(request, element.getId());
		} else if(element instanceof Tour) {
			return ToursManager.execute(request, element.getId());
		} else return false;
	}
	
	private static boolean sendRequest(Request request, Element element, Integer id2) {
		if(element instanceof PointOfInterest) {
			return POIsManager.execute(request, element.getId(), id2 );
		} else if(element instanceof Tour) {
			return ToursManager.execute(request, element.getId(), id2);
		}  else if(element instanceof Contest) {
			return ContestsManager.execute(request, element.getId(), id2);
		} else return false;
	}

	private static boolean checkActor(AbstractUser user) {
		return user.getRole() == Role.Contributor || user.getRole()== Role.AuthenticatedTourist;
	}
	
	}
