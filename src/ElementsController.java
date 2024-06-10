public class ElementsController {
	private POIManager poiManager;
	private ContentManager contentManager;
	private TourManager tourManager;
	
	public ElementsController() {
		this.poiManager = new POIManager();
		this.contentManager = new ContentManager();
		this.tourManager = new TourManager();
	}
	
	
	public boolean checkCorrectUser(AbstractUser user, Action action) {
		if ((user instanceof Contributor && user.getActions().contains(action))
				|| (user instanceof AuthorizedContributor && user.getActions().contains(action)
				|| (user instanceof Curator && user.getActions().contains(action))
				|| (user instanceof AuthenticatedTourist && user.getActions().contains(action))))
			return doAction(action);
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

}
