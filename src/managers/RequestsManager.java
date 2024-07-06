package managers;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import elements.Request;
import users.Action;

public class RequestsManager {
	private final POIsManager poiManager;
	private final ToursManager tourManager;
	private final ContestsManager contestManager;
	private final ValidationsManager validationManager;
	private final ReportsManager reportManager;
	private final AccountsManager accountManager;
	
	private final Queue<Request> pendingRequest;
	
	public RequestsManager(POIsManager poiManager, ToursManager tourManager,
			ContestsManager contestManager, ValidationsManager validationManager,
			ReportsManager reportManager, AccountsManager accountManager) {
		this.poiManager = poiManager;
		this.tourManager = tourManager;
		this.contestManager = contestManager;
		this.validationManager = validationManager;
		this.reportManager = reportManager;
		this.accountManager = accountManager;
		this.pendingRequest = new LinkedList<>();
	}

	private boolean sendRequest(Request request) {
		Action nextAction = request.getAction();
		if(nextAction==Action.CreatePOI || nextAction==Action.CreateContentInPOI) {
			poiManager.execute(request);
			return true;
			} else if(nextAction==Action.CreateTour || nextAction==Action.CreateContentInTour ||
					nextAction==Action.AddPOIInTour) {
			tourManager.execute(request);
			return true;
			} else if(nextAction==Action.CreateContest || nextAction==Action.CreateContentInContest) {
				return contestManager.execute(request);
				} else if(nextAction==Action.Validate) {
					return validationManager.execute(request);
				} else if(nextAction==Action.ReportContent) {
					return reportManager.execute(request);
				} else if(nextAction==Action.SaveElement || nextAction==Action.DefineRole) {
					return accountManager.execute(request);
					} else return false;
		} 
	
	
	public boolean addRequest(Request request) {
		return pendingRequest.add(request);
		
	}
	
	private boolean execute() {
		return sendRequest(pendingRequest.poll());
	}
	
}
	
	
	

