package com.finIntell.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		 http
				// 1. Disable CSRF protection
		 	.csrf(csrf ->csrf.disable())
		 	
		 	// 2. Configure authorization rules
		 	.authorizeHttpRequests(auth -> auth
		 			.requestMatchers("/api/auth/**").permitAll() //public end-point
		 			.requestMatchers("/api/admin/**").hasRole("ADMIN") //ADMIN end-point
		 			.anyRequest().authenticated() //All other requests require authentication
		 			)
		 	// 3. Configure form login
		 	.formLogin(Customizer.withDefaults()) // Allow everyone to access the login page
		 			
		 	// 4. Configure logout
		 	.httpBasic(Customizer.withDefaults()); // Enable HTTP Basic authentication for testing purposes
		
		 return http.build();
	}

	//Defining Password Encoder Bean
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
