package com.hsignz.config;

import java.io.IOException;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomeInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws IOException {
		TimeZone timezone = RequestContextUtils.getTimeZone(request);
		Locale locale = request.getLocale();
		log.debug(" Locale Name ---- ", locale.getCountry());
		log.debug("Time Zone is ---------- {}", timezone);
		log.debug("bodyttt Debuggg ----- {}", request.getRequestURL());
		String token = request.getHeader("Authorization");
		return true;

	}
}
