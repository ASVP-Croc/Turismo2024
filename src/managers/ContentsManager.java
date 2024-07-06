package managers;

import java.util.Scanner;

import elements.Content;
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
	
	private boolean sendValidation(Request request, Content content) {
		if(request.getUser().getRole()==Role.AuthenticatedTourist ||request.getUser().getRole()== Role.Contributor) {
			return validationManager.execute(request, content);
		} else return true;
		
	}
	
	public Content execute(Request request) {
		if(request.getAction()==Action.CreateContest) {
			Content content = create();
			sendValidation(request, content);
			return content;
			} else return null;
	}

}
