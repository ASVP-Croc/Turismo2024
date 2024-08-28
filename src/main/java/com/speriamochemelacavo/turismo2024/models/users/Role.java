package com.speriamochemelacavo.turismo2024.models.users;

import org.springframework.stereotype.Component;

@Component
public enum Role {
	Administrator,
	Animator,
	AuthenticatedTourist,
	AuthorizedContributor,
	Contributor,
	Curator,
	Tourist;
}
