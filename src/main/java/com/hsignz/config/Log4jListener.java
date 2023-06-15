package com.hsignz.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class Log4jListener implements ServletContextListener {
	private static final String LOG_PATTERN = "%d{dd-MM-yyyy HH:mm:ss} %-5p %c{1}:%L - %m%n";
	private static final String LOG_FILE = "./logs/spring-boot-logger.log";
	private static final String ARCHIVED_LOG_FILE = "./logs/archived/spring-boot-logger-%d{yyyy-MM-dd}.%i.log";

	@Override
	public void contextInitialized(ServletContextEvent sce) {

	}


	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
}
