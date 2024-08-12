package managers;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import elements.Content;
import elements.Contest;
import elements.Element;
import elements.Request;
import users.Action;
import users.Role;

public class ReportsManager{
	private final static Queue<Content> reportedContents = new LinkedList<>();
	
	

	public static boolean execute(Request request, Content content) {
		if(request.getAction()==Action.ReportContent) {
			return reportedContents.add(content);
		} return false;
	}
	
	public static boolean execute(Request request) {
		if(request.getAction()==Action.Validate && request.getUser().getRole()==Role.Curator) {
			return validateContents(request);
		}
		else return false;
	}

	private static boolean validateContents(Request request) {
		if(reportedContents.isEmpty()){ System.out.println("Benvenuto Curatore! Non ci sono Contenuti segnalati!");
		return true;
		} else { Scanner scanner = new Scanner(System.in);
			System.out.println("Benvenuto Animatore! Ci sono dei Contenuti da validare!");
			Integer select = 1;
			while(!reportedContents.isEmpty() && select==1) {
				Content content = reportedContents.poll();
				System.out.println(content.getText());
				System.out.println("1-per convalidare il Contenuto, 2-per scartare il Contenuto");
				Integer result = scanner.nextInt();
				if(result==2) {
					return deleteMessage(request, content.getReferencedElement(),content);
				}
				System.out.println("1-Visualizza il prossimo Contest per validare i contenuti, 2-Esci");
				select=scanner.nextInt();
				} return false;
			}
		}

	
	private static boolean deleteMessage(Request request, Element element, Content content) {
		Scanner scanner = new Scanner(System.in);
		Request next = new Request(request.getUser(), Action.Delete);
		sendRequest(next, element, content.getId());//notifica il Manager di eliminare l'elemento
		return true;
	}

	private static void sendRequest(Request next, Element element, Integer id) {
		// TODO Auto-generated method stub
		
	}
	
}
