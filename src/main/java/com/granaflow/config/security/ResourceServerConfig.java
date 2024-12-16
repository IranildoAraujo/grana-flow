package com.granaflow.config.security;

import java.util.Base64;
import java.util.List;

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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig {

	@Value("${security.jwt.token.secret-key}") // Chave secreta usada para assinar o JWT
	private String jwtSecretKey;
	
	private static final String ALGORITMO_DECODIFICADO = "HmacSHA256";

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.cors(cors -> cors.configurationSource(corsConfigurationSource())) // Configuração CORS
		.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated() // Protege todos os endpoints
		).oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.decoder(jwtDecoder())));
		return http.build();
	}

	@Bean
	JwtDecoder jwtDecoder() {
		byte[] decodedKey = Base64.getDecoder().decode(jwtSecretKey);
		SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, ALGORITMO_DECODIFICADO);
		return NimbusJwtDecoder.withSecretKey(originalKey).build();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();

	    // Definir origens permitidas - substituir pelos domínios reais da sua aplicação
	    configuration.setAllowedOrigins(List.of("http://localhost:3000", "http://www.granaflow.local:8080"));

	    // Métodos HTTP permitidos
	    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));

	    // Cabeçalhos permitidos
	    configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));

	    // Exposição de cabeçalhos específicos (caso necessário)
	    configuration.setExposedHeaders(List.of("Authorization"));

	    // Configuração para credenciais (se necessário, ex: cookies, autenticação baseada em sessão)
	    configuration.setAllowCredentials(true);

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}
}