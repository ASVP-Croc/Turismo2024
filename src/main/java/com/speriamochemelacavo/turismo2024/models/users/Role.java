package com.speriamochemelacavo.turismo2024.models.users;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{
	Administrator,
	Animator,
	AuthenticatedTourist,
	AuthorizedContributor,
	Contributor,
	Curator,
	Tourist;

	@Override
	public String getAuthority() {
		return this.toString().toUpperCase();
	}
}
