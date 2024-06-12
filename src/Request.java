
public class Request {
	private final AbstractUser user;
	private final Action action;
	
	public Request(AbstractUser user, Action action) {
		this.user = user;
		this.action = action;
	}
	
	public AbstractUser getUser() {
		return user;
	}
	
	public Action getAction() {
		return action;
	}
}
