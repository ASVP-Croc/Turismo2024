package managers;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import elements.Request;
import users.Action;

public class RequestsManager {
	private final POIManager poiManager;
	private final TourManager tourManager;
	private final NotificationManager notificationManager;
	
	private final Queue<Request> pendingRequest;
	
	public RequestsManager(POIManager poiManager, TourManager tourManager, NotificationManager notificationManager ) {
		this.poiManager = poiManager;
		this.tourManager = tourManager;
		this.notificationManager = notificationManager;
		this.pendingRequest = new LinkedList<>();
	}

	private boolean sendAction(Action action) throws IOException {
		if(action==Action.CreatePOI || action==Action.CreateContentPOI) {
			return poiManager.execute(action);
			} else if(action==Action.CreateTour || action==Action.CreateContentTour) {
			return tourManager.execute(action);
		} else return false;
	}
	
	public boolean addRequest(Request request) {
		return pendingRequest.add(request);
	}
	
	private void executeRequest() throws IOException {
		Request request = pendingRequest.poll();
		if(sendAction(request.getAction())) {
			notificationManager.sendMessage(request);
		};
	}
	
	public void execute() throws IOException {
		while(!pendingRequest.isEmpty()) {
			executeRequest();
		}
	}
	
	
	
}
