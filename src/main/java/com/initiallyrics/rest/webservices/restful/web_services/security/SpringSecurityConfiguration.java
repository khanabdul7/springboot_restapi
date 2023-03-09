package com.initiallyrics.rest.webservices.restful.web_services.security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	
	//Below, we are defining our custom filterChain for springSecurity.
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
//		1) All requests should be authenticated
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated()
				);
//		2) If a request is not authenticated, a web page is shown
		http.httpBasic(withDefaults());
		
//		3) CSRF -> POST, PUT
		http.csrf().disable();

		
		return http.build();
	}

}
