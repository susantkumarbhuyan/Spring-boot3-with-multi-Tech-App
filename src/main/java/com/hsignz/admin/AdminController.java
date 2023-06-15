package com.hsignz.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hsignz.common.classes.KeyCloakUserVo;
import com.hsignz.common.classes.NewPocDetails;
import com.hsignz.keycloak.TokenResponse;
import com.hsignz.keycloak.UserResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/admincontrol")
public class AdminController {
	@Autowired
	private AdminServices adminServices;

	@PostMapping(value = "/secure/createandupdatepocs", headers = "Accept=application/json")
	public NewPocDetails createAndUpdatePocs(@RequestBody NewPocDetails pocDetails) {
		try {
			return adminServices.createAndUpdatePocs(pocDetails);
		} catch (Exception e) {
			log.error("Error occurred in createAndUpdatePocs", e);

		}
		return null;
	}

	@PostMapping(value = "/createandupdatepocs/v2", headers = "Accept=application/json")
	public NewPocDetails createAndUpdatePocsV2(@RequestBody NewPocDetails pocDetails) {
		try {
			return adminServices.createAndUpdatePocsV2(pocDetails);
		} catch (Exception e) {
			log.error("Error occurred in createAndUpdatePocs", e);

		}
		return null;
	}

	@GetMapping("/token")
	public TokenResponse getAccessToke(@RequestParam String username, @RequestParam String password) {
		log.debug("Access Token Is accesing {}", 100);
		return adminServices.getAccessToke(username, password);

	}

	@GetMapping(value = "/createuser", headers = "Accept=application/json")
	public String createUser(@RequestBody KeyCloakUserVo user) {
		log.debug("Access Token Is using RefreshToken {}", 100);
		return adminServices.createUser(user);
	}

	@GetMapping(value = "/deleteuser", headers = "Accept=application/json")
	public String deleteUser(@RequestParam String username) {
		try {
			return adminServices.deleteUser(username);
		} catch (Exception e) {
			log.error("Error occurred in createAndUpdatePocs", e);
		}
		return null;
	}

	@GetMapping(value = "/getusers", headers = "Accept=application/json")
	public List<UserResponse> getUsers(@RequestParam String username) {
		try {
			return adminServices.getUsers(username);
		} catch (Exception e) {
			log.error("Error occurred in createAndUpdatePocs", e);
		}
		return null;
	}
}
