package com.hsignz.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.hsignz.common.constant.SecurityConstants;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	public static final String ADMIN = "admin";
	public static final String MANAGER = "manager";
	@Autowired
	private JwtAuthConverter jwtAuthConverter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfig -> jwtConfig
				.jwtAuthenticationConverter(jwtAuthConverter).jwkSetUri(SecurityConstants.JWT_JWK_URI)));

		// Enable and configure CORS
		http.cors(cors -> cors.configurationSource(corsConfigurationSource("http://localhost:8200")));

		// Disable CSRF because of state-less session-management
		http.csrf(csrf -> csrf.disable());

		// State-less session (state in access-token only)
		http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		// Return 401 (unauthorized) instead of 302 (redirect to login) when
		// authorization is missing or invalid
		http.exceptionHandling(eh -> eh.authenticationEntryPoint((request, response, authException) -> {
			response.addHeader(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"Restricted Content\"");
			response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
		}));

		http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
				.requestMatchers("/admincontrol/secure/**").hasAnyRole(ADMIN, MANAGER)
				.requestMatchers("/actuator/**", "/admincontrol/**").permitAll().anyRequest().authenticated());

		return http.build();
	}

	@Bean
	JwtDecoder jwtDecoder() {
		return JwtDecoders.fromIssuerLocation(SecurityConstants.JWT_ISSUER_URI);
	}

//	@Bean
//	MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
//		DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();
//		handler.setPermissionEvaluator(permissionEvaluator);
//		return handler;
//	}

	private UrlBasedCorsConfigurationSource corsConfigurationSource(String... origins) {
		final var configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList(origins));
		configuration.setAllowedMethods(List.of("*"));
		configuration.setAllowedHeaders(List.of("*"));
		configuration.setExposedHeaders(List.of("*"));
		configuration.setMaxAge(3600l);

		final var source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}