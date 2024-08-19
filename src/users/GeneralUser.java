package users;

import elements.Request;

public interface GeneralUser {
	
	Request sendRequest();
	
	Integer getId();
	
	Role getRole();
}
