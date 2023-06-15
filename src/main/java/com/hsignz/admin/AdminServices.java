package com.hsignz.admin;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import com.hsignz.common.classes.KeyCloakUserVo;
import com.hsignz.common.classes.NewPocDetails;
import com.hsignz.common.constant.KeyCloakConstants;
import com.hsignz.keycloak.KeyCloakClient;
import com.hsignz.keycloak.TokenResponse;
import com.hsignz.keycloak.UserResponse;
import com.hsignz.utils.CamundaUtils;
import com.hsignz.utils.LocalDateTimeTypeAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("adminServices")
public class AdminServices {

	@Autowired
	private CamundaUtils camundaUtils;
	@Autowired
	private PocMethods pocMethods;
	@Autowired
	private KeyCloakClient keyCloakClient;

	private final String bpmnProcessId = "poc_creation_bpmn_process";

	public NewPocDetails createAndUpdatePocs(NewPocDetails pocDetails) {
		log.debug("Creating or updating POC>> Details>> {}", new GsonBuilder()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter()).create().toJson(pocDetails));

		String pocDetailsstr = camundaUtils.startBPMNProcess(pocDetails, bpmnProcessId);
		ObjectMapper mapper = new ObjectMapper();
		try {
			pocDetails = mapper.readValue(pocDetailsstr, NewPocDetails.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return pocDetails;
	}

	@Transactional
	public NewPocDetails createAndUpdatePocsV2(NewPocDetails pocDetails) {
		NewPocDetails poc = pocMethods.setPocId(pocDetails);
		log.debug("In fetchFromDb Print >>>>>>>>>>>>>> " + pocDetails.toString());
		return poc;
	}

	public TokenResponse getAccessToke(String username, String password) {
		return keyCloakClient.getAccessToken(username, password, KeyCloakConstants.CLIENT_ID,
				KeyCloakConstants.CLIENT_SECRETE, "password");
	}

	public TokenResponse getAccessTokeUsingRefreshToken(String refreshToken) {
		TokenResponse response = keyCloakClient.getAccessTokeUsingRefreshToken(refreshToken,
				KeyCloakConstants.CLIENT_ID, KeyCloakConstants.CLIENT_SECRETE, "refresh_token");
		log.debug("getAccesToken  ---- {}", response.toString());
		return response;
	}

	public String createUser(KeyCloakUserVo user) {
		String response = "";
		String token = keyCloakClient.getClientAccessToken(KeyCloakConstants.CLIENT_ID,
				KeyCloakConstants.CLIENT_SECRETE, "client_credentials").access_token();
		String accessToken = String.format("Bearer %s", token);
		log.debug("Admin Access Token {}", accessToken);

		try {
			keyCloakClient.createUser(Map.of("Authorization", accessToken), user);
			response = "User is Successfully Created";

		} catch (WebClientResponseException.Conflict e) {
			response = "User is Already Exist";
		}
		log.debug("getAccesToken  ---- {}");
		return response;
	}

	public String deleteUser(String username) {
		String token = keyCloakClient.getClientAccessToken(KeyCloakConstants.CLIENT_ID,
				KeyCloakConstants.CLIENT_SECRETE, "client_credentials").access_token();
		String accessToken = String.format("Bearer %s", token);
		log.debug("Admin Access Token {}", accessToken);
		List<UserResponse> users = keyCloakClient.getUsers(Map.of("Authorization", accessToken), username, true);
		if (users.size() <= 0) {
			return "Invelid User";
		}
		keyCloakClient.deleteUser(Map.of("Authorization", accessToken), users.get(0).id());
		log.debug("getAccesToken  ---- {}");
		return "User Successfully removed";
	}

	public List<UserResponse> getUsers(String username) {
		String token = keyCloakClient.getClientAccessToken(KeyCloakConstants.CLIENT_ID,
				KeyCloakConstants.CLIENT_SECRETE, "client_credentials").access_token();
		String accessToken = String.format("Bearer %s", token);
		log.debug("Admin Access Token {}", accessToken);
		return keyCloakClient.getUsers(Map.of("Authorization", accessToken), username, true);
	}
}
