package com.speriamochemelacavo.turismo2024.users;

import com.speriamochemelacavo.turismo2024.elements.Request;

public interface GeneralUser {
	
	Request sendRequest();
	
	Integer getId();
	
	Role getRole();
}
