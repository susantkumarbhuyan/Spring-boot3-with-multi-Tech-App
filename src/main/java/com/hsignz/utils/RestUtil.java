package com.hsignz.utils;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.hsignz.common.classes.NewPocDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestUtil {

	public List<NewPocDetails> getTweetsBlocking() {
		
		log.info("Starting BLOCKING Controller!");
		final var uri = "";

		var restTemplate = new RestTemplate();
		ResponseEntity<List<NewPocDetails>> response = restTemplate.exchange(uri, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<NewPocDetails>>() {
				});

		var result = response.getBody();
		result.forEach(tweet -> log.info(tweet.toString()));
		log.info("Exiting BLOCKING Controller!");
		return result;
	}
}
