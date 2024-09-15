package com.speriamochemelacavo.turismo2024.models.users;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{
	ADMINISTRATOR,
	ANIMATOR,
	AUTHENTICATED_TOURIST,
	AUTHORIZED_CONTRIBUTOR,
	CONTRIBUTOR,
	CURATOR;

	@Override
	public String getAuthority() {
		return name();
	}
}
