package com.naukri.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("My Spring Boot API")
                        .version("1.0")
                        .description("API documentation for my application"));
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        // 1. Disable CSRF (Crucial for testing POST/PUT requests via Swagger)
	        .csrf(csrf -> csrf.disable()) 
	        
	        .authorizeHttpRequests(auth -> auth
	            // 2. Allow Swagger UI & Docs
	            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
	            
	            // 3. Allow your actual Controller endpoints
	            .requestMatchers("/api/v1/**").permitAll()
	            
	            // 4. Everything else requires a login
	            .anyRequest().authenticated()
	        );

	    return http.build();
	}
        
}
