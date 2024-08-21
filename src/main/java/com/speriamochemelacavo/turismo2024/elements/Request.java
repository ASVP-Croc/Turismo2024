package com.speriamochemelacavo.turismo2024.elements;

import com.speriamochemelacavo.turismo2024.users.Action;
import com.speriamochemelacavo.turismo2024.users.GeneralUser;

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
