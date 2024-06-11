import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class ElementsController {
	private final POIManager poiManager;
	private final TourManager tourManager;
	
	private final Queue<Action> pendingRequest;
	
	public ElementsController(POIManager poiManager, TourManager tourManager) {
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
