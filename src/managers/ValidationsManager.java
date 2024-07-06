package managers;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import elements.*;
import users.*;

public class ValidationsManager {
	private final POIsManager poiManager;
	private final ToursManager tourManager;
	private final ContestsManager contestManager;
	private final NotificationsManager notificationManager;
	private final Queue<Element> elementList;
	private final Queue<Content> contestList;
	
	public ValidationsManager(POIsManager poiManager, ToursManager tourManager, ContestsManager contestManager, NotificationsManager notificationManager) {
		this.poiManager = poiManager;
		this.tourManager = tourManager;
		this.contestManager = contestManager;
		this.notificationManager = notificationManager;
		this.elementList = new LinkedList();
		this.contestList = new LinkedList();
	}

	public boolean execute(Request request) {
		if(request.getAction()==Action.Validate && request.getUser().getRole()==Role.Curator) {
			return validateElements(request);
		} else if (request.getAction()==Action.Validate && request.getUser().getRole()==Role.Animator) {
			return validateContentInContest(request);
			} else return false;
	}
	
	public boolean execute(Request request, Content content) {
		return pendingValidation(request,content);
	}
	
	public boolean execute(Request request, Element element) {
		return pendingValidation(request, element);
	}
	
	private boolean validateContentInContest(Request request) {
		if(contestList.isEmpty()) { System.out.println("Benvenuto Animatore! non ci sono contenuti da"
				+ "validare al momento!");
		return false;}
		else System.out.println("Benvenuto Animatore! ci sono dei contenuti da validare!");
		Scanner scanner = new Scanner(System.in);
		while(!contestList.isEmpty()){
			Content content = contestList.poll();
			System.out.println(content.getText());
			System.out.println("Validare il contenuto? Si/No");
			String result = scanner.nextLine();
			if(result.equals("No")) {
				System.out.println("Inserisci una motivazione per la mancata validazione: ");
				String reason = scanner.nextLine();
				Request next = new Request(request.getUser(), Action.Delete);
				sendRequest(next, content.getId());
				sendNotification(reason, next);
				return true;
			} else if(result.equals("Si")) {
				Request next = new Request(request.getUser(), Action.Post);
				sendRequest(next, content.getId());
			    sendNotification("",next);
				System.out.println("Il contenuto è stato pubblicato ed è ora visibile sulla piattaforma!");
				return true;
			}
		}
		return false;
		
	}

	private boolean sendNotification(String text, Request request) {
		return notificationManager.execute(text,request);
	}

		

	private boolean sendRequest(Request request, Integer id) {
		if(id.intValue()==1) {
			return poiManager.execute(request, id);
		} else if(id.intValue()==2) {
			return tourManager.execute(request, id);
		}  else return false;
	}
	
	private boolean sendRequest(Request request, Integer id1, Integer id2) {
		if(id1.intValue()==1) {
			return poiManager.execute(request, id1, id2 );
		} else if(id1.intValue()==2) {
			return tourManager.execute(request, id1, id2);
		}  else return false;
	} 
	

	private boolean validateElements(Request request) {
		if(elementList.isEmpty()) { System.out.println("Benvenuto Curatore! non ci sono contenuti da"
				+ "validare al momento!");
		return false;}
		else System.out.println("Benvenuto Curatore! ci sono dei contenuti da validare!");
		Scanner scanner = new Scanner(System.in);
		while(!elementList.isEmpty()){
			Element element = elementList.poll();
			System.out.println(element);
			System.out.println("Validare il contenuto? Si/No");
			String result = scanner.nextLine();
			if(result.equals("No")) {
				System.out.println("Inserisci una motivazione per la mancata validazione: ");
				String reason = scanner.nextLine();
				Request next = new Request(request.getUser(), Action.Delete);
				sendRequest(next, element.getId());
				sendNotification(reason, next);
				
			} else if(result.equals("Si")) {
				Request next = new Request(request.getUser(), Action.Post);
				sendRequest(next, element.getId());
			    sendNotification("",next);
				System.out.println("Il contenuto è stato pubblicato ed è ora visibile sulla piattaforma!");
			}}return true;
		
	}

	private boolean checkActor(AbstractUser user) {
		return user.getRole() == Role.Contributor || user.getRole()== Role.AuthenticatedTourist;
	}
	
	private boolean pendingValidation(Request request, Content content) {
		return contestList.add(content);
	}
	
	private boolean pendingValidation(Request request, Element element) {
		return elementList.add(element);
	}

}
