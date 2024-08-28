package com.speriamochemelacavo.turismo2024.models.users;

import java.util.Scanner;

public class User implements GeneralUser {

	@Override
	public Role getRole() {
		return Role.Tourist;
	}
		
}
