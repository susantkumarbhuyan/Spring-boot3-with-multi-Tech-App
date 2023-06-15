package com.hsignz.keycloak;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

import com.hsignz.common.classes.KeyCloakUserVo;

@HttpExchange("/auth")
public interface KeyCloakClient {
	@PostExchange(value = "/realms/HSKeyCloak/protocol/openid-connect/token", contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public TokenResponse getAccessToken(@RequestParam String username, @RequestParam String password,
			@RequestParam String client_id, @RequestParam String client_secret, @RequestParam String grant_type);
	
	@PostExchange(value = "/realms/HSKeyCloak/protocol/openid-connect/token", contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public TokenResponse getClientAccessToken(@RequestParam String client_id, @RequestParam String client_secret,
			@RequestParam String grant_type);

	@PostExchange(value = "/realms/HSKeyCloak/protocol/openid-connect/token", contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public TokenResponse getAccessTokeUsingRefreshToken(@RequestParam String refresh_token,
			@RequestParam String client_id, @RequestParam String client_secret, @RequestParam String grant_type);

	@PostExchange(value = "/admin/realms/HSKeyCloak/users")
	public void createUser(@RequestHeader Map<String, String> header, @RequestBody KeyCloakUserVo keyCloakUserVo);

	@DeleteExchange(value = "/admin/realms/HSKeyCloak/users/{id}")
	public void deleteUser(@RequestHeader Map<String, String> header, @PathVariable String id);

	@GetExchange(value = "/admin/realms/HSKeyCloak/users")
	public List<UserResponse> getUsers(@RequestHeader Map<String, String> header, @RequestParam String username,
			@RequestParam boolean exact);

	@PutExchange(value = "/admin/realms/HSKeyCloak/users/{id}")
	public void updateUser(@RequestHeader Map<String, String> header, @PathVariable String id,
			@RequestBody KeyCloakUserVo keyCloakUserVo);
}
