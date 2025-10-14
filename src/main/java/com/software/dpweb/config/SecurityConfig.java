package com.software.dpweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.software.dpweb.filter.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
		.csrf(csrf->csrf.disable())
		
		.authorizeHttpRequests(auth->
		        auth.requestMatchers("/auth/login","/auth/create-account").permitAll()
		     // auth.requestMatchers("/auth/**").permitAll()
		        .requestMatchers("/student/**").hasRole("STUDENT")
		        .requestMatchers("/admin/**").hasRole("ADMIN")
		        .requestMatchers("/user/**").hasRole("USER")
				    .anyRequest().authenticated()
				).sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			   );
		
		
		http.addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter.class);
		return http.build();
		
	}
	
	
	
	
	
	
	

}
