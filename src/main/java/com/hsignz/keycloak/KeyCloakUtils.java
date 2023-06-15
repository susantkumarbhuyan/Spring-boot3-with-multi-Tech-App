package com.hsignz.keycloak;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.hsignz.common.constant.KeyCloakConstants;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Configuration
public class KeyCloakUtils {

	public WebClient webClient() {
		HttpClient httpClient = HttpClient.newConnection().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
				.doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(10))
						.addHandlerLast(new WriteTimeoutHandler(10)));
		ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

		return WebClient.builder().baseUrl(KeyCloakConstants.BASE_URL).clientConnector(connector).build();
	}

	@Bean
	KeyCloakClient keyCloackClient() {
		HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
				.builder(WebClientAdapter.forClient(webClient())).build();
		return httpServiceProxyFactory.createClient(KeyCloakClient.class);
	}
}
