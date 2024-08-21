package com.speriamochemelacavo.turismo2024.managers;

import java.util.LinkedList;
import java.util.Queue;

import com.speriamochemelacavo.turismo2024.elements.Request;
import com.speriamochemelacavo.turismo2024.users.Action;

public class RequestsManager {
	private final static Queue<Request> pendingRequest = new LinkedList();
	
	private static void sendRequest(Request request) {
		Action nextAction = request.getAction();
		if(nextAction==Action.CreatePOI || nextAction==Action.CreateContentInPOI ||  nextAction==Action.GetPOIs) {
			POIsManager.execute(request);
			} else if(nextAction==Action.CreateTour || nextAction==Action.CreateContentInTour ||
					nextAction==Action.AddPOIInTour ||  nextAction==Action.GetTours) {
			ToursManager.execute(request);
			} else if(nextAction==Action.CreateContest || nextAction==Action.CreateContentInContest ||  nextAction==Action.GetContests) {
				ContestsManager.execute(request);
				} else if(nextAction==Action.Validate) {
					ValidationsManager.execute(request);
				} else if(nextAction==Action.DefineRole || nextAction==Action.Registration || nextAction==Action.Login) {
					AccountsManager.execute(request);
					};
		} 
	
	
	private static boolean addRequest(Request request) {
		return pendingRequest.add(request);
		
	}
	
	public static void execute(Request request) {
		addRequest(request);
		sendRequest(pendingRequest.poll());
	}
	
	
	
}
	
	
	

