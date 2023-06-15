package com.hsignz.common.constant;

public class SecurityConstants {
	public final static String JWT_RESOURCE_ACCESS_ID = "hs-spring-keycloak";
	public final static String JWT_PRINCIPAL_ATTRIBUTE = "preferred_username";
	public final static String JWT_ISSUER_URI = "http://localhost:18080/auth/realms/HSKeyCloak";
	public final static String JWT_JWK_URI ="http://localhost:18080/auth/realms/HSKeyCloak/protocol/openid-connect/certs";
}
