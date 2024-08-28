package com.speriamochemelacavo.turismo2024.managers;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import com.speriamochemelacavo.turismo2024.elements.Content;
import com.speriamochemelacavo.turismo2024.elements.Contest;
import com.speriamochemelacavo.turismo2024.elements.Element;
import com.speriamochemelacavo.turismo2024.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.elements.Request;
import com.speriamochemelacavo.turismo2024.elements.Tour;
import com.speriamochemelacavo.turismo2024.users.Action;
import com.speriamochemelacavo.turismo2024.users.Role;

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
					return deleteMessage(request, content.getReferenced(),content);
				}
				System.out.println("1-Visualizza il prossimo Contest per validare i contenuti, 2-Esci");
				select=scanner.nextInt();
				} return false;
			}
		}

	
	private static boolean deleteMessage(Request request, Element element, Content content) {
		Scanner scanner = new Scanner(System.in);
		Request next = new Request(request.getUser(), Action.Delete);
		sendRequest(next, element, content.getId());
		return true;
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
	
}
