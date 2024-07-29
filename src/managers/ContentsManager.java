package managers;

import java.util.Scanner;

import elements.Content;
import elements.Contest;
import elements.Element;
import elements.Request;
import users.Action;
import users.Role;

public class ContentsManager {
	
	private static Content create(Request request, Element element) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Inserisci un contenuto:  ");
		String description = scanner.nextLine();
		Content content = new Content(description, request.getUser().getRole());
		sendValidation(request, element);
		return content;
	}
	
	private static boolean sendValidation(Request request, Element element) {
		if(request.getUser().getRole()==Role.AuthenticatedTourist ||request.getUser().getRole()== Role.Contributor) {
			return ValidationsManager.execute(request, element);
		} else return true;
		
	}
	
	private static boolean sendValidation(Request request, Contest contest) {
		if(request.getUser().getRole()==Role.AuthenticatedTourist ||request.getUser().getRole()== Role.Contributor) {
			return ValidationsManager.execute(request, contest);
		} else return true;
		
	}
	
	public static Content execute(Request request, Element element) {
		if(request.getAction()==Action.CreateContentInPOI || request.getAction()==Action.CreateContentInTour) {
			Content content = create(request, element);
			return content;
		} else return null;
	}
	public static Content execute(Request request, Contest element) {
		if(request.getAction()==Action.CreateContentInContest) {
		Content content = create(request, element);
		return content;
		} else return null;
}
	
}
