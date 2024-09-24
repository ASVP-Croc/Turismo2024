package com.speriamochemelacavo.turismo2024.models.users;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{
	ROLE_ADMINISTRATOR,
	ROLE_ANIMATOR,
	ROLE_AUTHENTICATED_TOURIST,
	ROLE_AUTHORIZED_CONTRIBUTOR,
	ROLE_CONTRIBUTOR,
	ROLE_CURATOR,
	ROLE_TOURIST;

	@Override
	public String getAuthority() {
		return name();
	}
}
