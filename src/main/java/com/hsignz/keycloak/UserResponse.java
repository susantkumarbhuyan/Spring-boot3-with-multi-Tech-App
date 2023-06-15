package com.hsignz.keycloak;

import java.util.List;

public record UserResponse(String id, long createdTimestamp, String username, boolean enabled, boolean totp,
		boolean emailVerified, String firstName, String lastName, String email, List<Object> disableableCredentialTypes,
		List<Object> requiredActions, int notBefore, Access access) {
}

record Access(boolean manageGroupMembership, boolean view, boolean mapRoles, boolean impersonate, boolean manage) {
}