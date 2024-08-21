package com.speriamochemelacavo.turismo2024.users;

public class AuthenticatedUser extends AbstractAuthenticatedUser {

	public AuthenticatedUser(String name, String surname, String userName, String email,String phoneNumber, Role role) {
		super(name, surname, userName, email, phoneNumber, role);
	}

}
