package com.speriamochemelacavo.turismo2024.users;

import com.speriamochemelacavo.turismo2024.elements.Request;

public interface GeneralUser {
	
	Request sendRequest();
	
	int getId();
	
	Role getRole();
}
