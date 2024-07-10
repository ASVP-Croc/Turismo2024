package managers;

import java.util.Scanner;

import elements.Content;
import elements.Contest;
import elements.Element;
import elements.Request;
import users.Action;
import users.Role;

public class ContentsManager {
	private final ValidationsManager validationManager;
	
	
	public ContentsManager(ValidationsManager validationManager) {
		this.validationManager = validationManager;
		}
	
	private Content create() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Inserisci un contenuto:  ");
		String description = scanner.nextLine();
		return new Content(description);
	}
	
	private boolean sendValidation(Request request, Element element) {
		if(request.getUser().getRole()==Role.AuthenticatedTourist ||request.getUser().getRole()== Role.Contributor) {
			return validationManager.execute(request, element);
		} else return true;
		
	}
	
	private boolean sendValidation(Request request, Contest contest) {
		if(request.getUser().getRole()==Role.AuthenticatedTourist ||request.getUser().getRole()== Role.Contributor) {
			return validationManager.execute(request, contest);
		} else return true;
		
	}
	
	public Content execute(Request request, Element element) {
		if(request.getAction()==Action.CreateContentInPOI || request.getAction()==Action.CreateContentInTour) {
			Content content = create();
			sendValidation(request, element);
			return content;
		} else return null;
	}
	public Content execute(Request request, Contest element) {
		if(request.getAction()==Action.CreateContentInContest) {
		Content content = create();
		sendValidation(request, element);
		return content;
		} else return null;
}
	
}
