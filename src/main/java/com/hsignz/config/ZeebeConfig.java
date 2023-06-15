package com.hsignz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.hsignz.common.constant.ZeebeConstants;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.impl.ZeebeClientBuilderImpl;

@Configuration
public class ZeebeConfig {
	@Primary
	@Bean
	ZeebeClient zeebeCustomeClient() {
		return new ZeebeClientBuilderImpl().gatewayAddress(ZeebeConstants.ZEEBE_GATEWAY_ADDRESS).usePlaintext().build();
	}

}
