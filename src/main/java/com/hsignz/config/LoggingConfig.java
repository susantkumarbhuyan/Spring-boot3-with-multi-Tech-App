package com.hsignz.config;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.LoggerConfig.RootLogger;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.spi.LoggerContext;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@EnableAutoConfiguration
public class LoggingConfig {

	private static final String LOG_PATTERN = "%d{dd-MM-yyyy HH:mm:ss} %-5p %c{1}:%L - %m%n";
	private static final String LOG_FILE = "./logs/spring-boot-logger.log";
	private static final String ARCHIVED_LOG_FILE = "./logs/archived/spring-boot-logger-%d{yyyy-MM-dd}.%i.log";

//
//	@Bean
//	LoggerContext loggerContext() {
//				PatternLayout layout = PatternLayout.newBuilder().withPattern(LOG_PATTERN).build();
//		ConsoleAppender consoleAppender = ConsoleAppender.newBuilder().setLayout(layout).setName("LogToConsole").build();
//		
//		RootLogger.newRootBuilder().withLevel(Level.DEBUG).withRefs(null);
//		return (LoggerContext) LoggerFactory.getILoggerFactory();
//	}
//
//	@Bean
//	PatternLayoutEncoder patternLayoutEncoder(LoggerContext loggerContext) {
//		PatternLayoutEncoder encoder = new PatternLayoutEncoder();
//		encoder.setPattern(LOG_PATTERN);
//		encoder.setContext(loggerContext);
//		encoder.start();
//		return encoder;
//	}
//
//	@Bean
//	ConsoleAppender<ILoggingEvent> consoleAppender(LoggerContext loggerContext, PatternLayoutEncoder encoder) {
//		ConsoleAppender<ILoggingEvent> appender = new ConsoleAppender<>();
//		appender.setName("Console");
//		appender.setEncoder(encoder);
//		appender.setContext(loggerContext);
//		appender.start();
//		return appender;
//	}
//
//	@Bean
//	RollingFileAppender<ILoggingEvent> rollingFileAppender(LoggerContext loggerContext, PatternLayoutEncoder encoder) {
//		RollingFileAppender<ILoggingEvent> appender = new RollingFileAppender<>();
//		appender.setName("RollingFile");
//		appender.setEncoder(encoder);
//		appender.setFile(LOG_FILE);
//
//		TimeBasedRollingPolicy<ILoggingEvent> rollingPolicy = new TimeBasedRollingPolicy<>();
//		rollingPolicy.setFileNamePattern(ARCHIVED_LOG_FILE);
//		rollingPolicy.setMaxHistory(7);
//
//		SizeAndTimeBasedFNATP<ILoggingEvent> triggeringPolicy = new SizeAndTimeBasedFNATP<>();
//		triggeringPolicy.setMaxFileSize(FileSize.valueOf("10MB"));
//
//		rollingPolicy.setTimeBasedFileNamingAndTriggeringPolicy(triggeringPolicy);
//		rollingPolicy.setParent(appender);
//		rollingPolicy.setContext(loggerContext);
//		rollingPolicy.start();
//
//		appender.setRollingPolicy(rollingPolicy);
//		appender.setContext(loggerContext);
//		appender.start();
//
//		return appender;
//	}
//
//	@Bean
//	Logger rootLogger() {
////		ch.qos.logback.classic.Logger rootLogger = loggerContext.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
////        rootLogger.setLevel(Level.INFO);
////        rootLogger.addAppender(consoleAppender);
////        rootLogger.addAppender(rollingFileAppender);
//
//		// Add additional loggers with different levels and appenders
////        ch.qos.logback.classic.Logger logger1 = loggerContext.getLogger("com.hsignz");
////        logger1.setLevel(Level.DEBUG);
////        logger1.addAppender(consoleAppender);
////        logger1.addAppender(rollingFileAppender);
//
//		PatternLayout layout = PatternLayout.newBuilder().withPattern(LOG_PATTERN).build();
//		ConsoleAppender consoleAppender = ConsoleAppender.newBuilder().setLayout(layout).setName("Console").build();
//		consoleAppender.start();
//
//		// Logger rootLogger = loggerContext.getLogger("com.hsignz");
//		Logger rootLogger = (Logger) LoggerFactory.getLogger("com.hsignz");
//		rootLogger.setLevel(Level.DEBUG);
//		rootLogger.setAdditive(false);
//		rootLogger.addAppender(consoleAppender);
//		return rootLogger;
//	}
}