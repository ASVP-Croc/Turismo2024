package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.Contest;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;

@Service
public class ContestsService extends ElementsWithContentsService<Contest>  {
	
	public ContestsService() {
		super();
	}
	
//	
//	public static Contest getContest(Integer id) {
//		return contests.get(id);
//	}
//	
//	public static Stream<Contest> getContests() {
//		return contests.values().stream();
//	}
	
//	public static Contest execute(Request request) {
//		Action action = request.getAction();
//		if(action==Action.CreateContest) {
//			return createContest(request);
//		} else if(action==Action.CreateContentInContest) {
//			return addContentToContest(request);
//		} else if(action==Action.GetContests) showContests(request);
//		return null;
//	}
	
//	public 
//	
//	private static void showContests(Request request) {
//		Scanner scanner = new Scanner(System.in);
//		getContests().forEach(elem->System.out.println("Dsc: " + elem.getDescription()+ " Id: "+elem.getId()));
//		System.out.println("Inserisci l'ID per visualizzare i Contenuti del Contest: ");
//		Integer id = scanner.nextInt();
//		showContentsInContest(contests.get(id), request);
//		}
//		
//	
//	private static void showContentsInContest(Contest contest, Request request) {
//		Scanner scanner = new Scanner(System.in);
//		contest.getContents().forEach(elem->System.out.println("Dsc: " + elem.getText()+ " Id: "+elem.getId()));
//		System.out.println("1-Inserisci nuovo contenuto al contest, 2-Salva Elemento, 3-Segnala Contenuto, 4-Esci"+
//		contest.getDescription()+" " +contest.getId());
//		Integer select = scanner.nextInt();
//		if(select==1) {
//			addContentToContest(contest, request);
//		} else if(select==2) {
//			Request nextRequest = new Request(request.getUser(), Action.SaveElement);
//			AccountsManager.execute(request, contest);
//		} else if(select==3) {
//			Request nextRequest = new Request(request.getUser(), Action.ReportContent);
//			ReportsManager.execute(request);
//		} else showContests(request);
//	}
//	
//	private static Contest addContentToContest(Request request) {
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Seleziona un Contest a cui aggiungere un Contenuto:");
//		getContests().forEach(elem->System.out.println("Dsc: " + elem.getDescription()+ " Id: "+elem.getId()));
//		System.out.println("Insrisci l'ID per selzionare il Contest: ");
//		Integer id = scanner.nextInt();
//		return addContentToContest(contests.get(id), request);
//		}
//	
//	private static Contest addContentToContest(Contest contest, Request request) {
//		Request nextRequest = new Request(request.getUser(), Action.CreateContentInContest);
//		contest.addContent(ContentsManager.execute(nextRequest, contest));
//		return contest;
//	}
//
//	private static Contest createContest(AuthenticatedUser author) {
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Inserisci una descrizione per il Contest: ");
//		String description = scanner.nextLine();
//		System.out.println("Seleziona 1-POI o 2-Itinerario per associare un luogo al Contest.");
//		int select = scanner.nextInt();
//		Contest contest = null;
//		if(select==1) {
//			System.out.println("Seleziona un POI inserendo l'ID.");
//			POIsManager.getPOIs().forEach(elem-> System.out.println("Dsc: " + elem.getDescription()+ " Id: "+elem.getId()));
//			Integer id = scanner.nextInt();
//			contest = new Contest(description, author);
//		} else if(select==2) {
//			ToursManager.getTours();
//			System.out.println("Seleziona un Tour inserendo l'ID.");
//			ToursManager.getTours().forEach(elem-> System.out.println("Dsc: " + elem.getDescription()+ " Id: "+elem.getId()));
//			Integer id = scanner.nextInt();
//			contest = new Contest(description, author);
//		}return  contests.put(contest.getId(), contest);
//	}
//	
//	public static boolean execute(Request request, Integer id1, Integer id2) {
//		if(request.getAction()==Action.Post) {
//			getContest(id1).getContent(id2).setPublished(true);;
//			return true;
//		} else if(request.getAction()==Action.Delete) {
//			getContest(id1).deleteContent(id2);
//			return true;
//		} else return false;
//	}
	
}
