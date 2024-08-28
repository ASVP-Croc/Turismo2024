package com.speriamochemelacavo.turismo2024.users;

import java.util.Scanner;

public class User implements GeneralUser {

	@Override
	public Role getRole() {
		return Role.Tourist;
	}
		
}
