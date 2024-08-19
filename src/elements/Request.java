package elements;

import users.Action;
import users.GeneralUser;

public class Request {
	private final GeneralUser user;
	private final Action action;
	
	public Request(GeneralUser user, Action action) {
		this.user = user;
		this.action = action;
	}
	
	public GeneralUser getUser() {
		return user;
	}
	
	public Action getAction() {
		return action;
	}
}
