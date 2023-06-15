package com.hsignz.common.classes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record KeyCloakUserVo(String username, String email, String firstName, String lastName, boolean enabled,
		boolean emailVerified) {
}
