package com.granaflow.config.security;

import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig {

	@Value("${security.jwt.token.secret-key}") // Chave secreta usada para assinar o JWT
	private String jwtSecretKey;
	
	private static final String ALGORITMO_DECODIFICADO = "HmacSHA256";

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated() // Protege todos os endpoints
		).oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.decoder(jwtDecoder())));
		return http.build();
	}

	@Bean
	JwtDecoder jwtDecoder() {
		byte[] decodedKey = Base64.getDecoder().decode(jwtSecretKey);
		SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, ALGORITMO_DECODIFICADO);
		return NimbusJwtDecoder.withSecretKey(originalKey).build();
	}
}