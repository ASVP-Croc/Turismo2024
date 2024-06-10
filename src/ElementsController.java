 import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ElementsController {
	private POIManager poiManager;
	private ContentManager contentManager;
	private TourManager tourManager;
	
	private Map<Action, Integer> requests;
	
	private Queue<Action> pendingRequest;
	
	public ElementsController() {
		this.poiManager = new POIManager();
		this.contentManager = new ContentManager();
		this.tourManager = new TourManager();
		this.requests = new HashMap<>();
	}
	
	
	private boolean checkCorrectUser(AbstractUser user, Action action) {
		if ((user instanceof Contributor && user.getActions().contains(action))
				|| (user instanceof AuthorizedContributor && user.getActions().contains(action)
				|| (user instanceof Curator && user.getActions().contains(action))
				|| (user instanceof AuthenticatedTourist && user.getActions().contains(action))))
			return true;
		else return false;
	}

	private boolean doAction(Action action) {
		if(action==Action.CreatePOI) {
			return poiManager.create("ProvaPOI", new Coordinate(5,10));
			
		} else if(action==Action.CreateTour) {
			return tourManager.create("ProvaTour");
			
		} else if(action==Action.CreateContent) {
			return poiManager.getPois().get(0).getContents().add(contentManager.create("ProvaContent"));
		} else return false;
		
	}
	
	private boolean executeRequest(AbstractUser user, Action action) {
		return checkCorrectUser(user, action) && doAction(action);
	}
	
	public boolean execute(AbstractUser user) {
		while(pendingRequest.isEmpty()) {
			executeRequest(user,pendingRequest.poll());
		} return false;
	}
	
	public boolean addAction(Action action) {
		return pendingRequest.add(action);
	}
	

}
