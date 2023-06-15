package com.hsignz.keycloak;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public record TokenResponse(String access_token, long expires_in, long refresh_expires_in, String refresh_token,
		String token_type, String scope, String session_state) {
}
