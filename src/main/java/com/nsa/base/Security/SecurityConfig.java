
//  first one which is executed

package com.nsa.base.Security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

	@Autowired
	JwtAuthFilter jwtFilter;
	  
	  @Value("${app.cors.allowedOrigins:*}")
	  private List<String> allowed;

	  @Bean 
	  PasswordEncoder passwordEncoder() {
		  return new BCryptPasswordEncoder(); }

	  @Bean
	  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.csrf(csrf -> csrf.disable())
	      .cors(cors -> cors.configurationSource(corsSource()))
	      .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	      .authorizeHttpRequests(auth -> auth
	        .requestMatchers("/api/auth/**", "/ws/**").permitAll()
	        .requestMatchers(HttpMethod.GET, "/actuator/health").permitAll()
	        .anyRequest().authenticated()
	      );
	    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	    return http.build();
	  }

	  private CorsConfigurationSource corsSource() {
	    var cfg = new CorsConfiguration();
	    cfg.setAllowedOrigins(allowed);
	    cfg.setAllowedMethods(List.of("GET","POST","PUT","DELETE","PATCH","OPTIONS"));
	    cfg.setAllowedHeaders(List.of("*"));
	    cfg.setAllowCredentials(true);
	    var source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", cfg);
	    return source;
	  }

	  @Bean
	  AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	    return config.getAuthenticationManager();
	  }
}
