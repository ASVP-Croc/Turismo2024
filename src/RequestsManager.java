import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class RequestsManager {
	private final POIManager poiManager;
	private final TourManager tourManager;
	
	private final Queue<Action> pendingRequest;
	
	public RequestsManager(POIManager poiManager, TourManager tourManager) {
		this.poiManager = poiManager;
		this.tourManager = tourManager;
		this.pendingRequest = new LinkedList<>();
	}

	private boolean sendAction(Action action) throws IOException {
		if(action==Action.CreatePOI || action==Action.CreateContentPOI) {
			return poiManager.execute(action);
			} else if(action==Action.CreateTour || action==Action.CreateContentTour) {
			return tourManager.execute(action);
		} else return false;
	}
	
	public boolean addRequest(Action action) {
		return pendingRequest.add(action);
	}
	
	private void executeRequest() throws IOException {
		sendAction(pendingRequest.poll());
	}
	
	public void execute() throws IOException {
		while(!pendingRequest.isEmpty()) {
			executeRequest();
		}
	}
	
	
	
}
