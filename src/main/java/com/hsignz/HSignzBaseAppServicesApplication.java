package com.hsignz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.camunda.zeebe.spring.client.annotation.Deployment;

@SpringBootApplication
@ComponentScan(basePackages = "com.hsignz")
@Deployment(resources = { "classpath*:*.bpmn", "classpath*:*.dmn" })
public class HSignzBaseAppServicesApplication {
	private static final Logger logger = LoggerFactory.getLogger(HSignzBaseAppServicesApplication.class);

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(HSignzBaseAppServicesApplication.class);
		logger.debug("Debug Point is Started here -----------------------");
		application.run(args);
	}

}
