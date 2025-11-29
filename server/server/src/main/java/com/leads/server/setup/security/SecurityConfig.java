package com.leads.server.setup.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CorsConfigurationSource corsConfigurationSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .headers(this::headers)
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/api/public/**")
                                .permitAll()
                                .requestMatchers("/api/app/**")
                                .authenticated()
                                .anyRequest()
                                .denyAll())
                .cors(cors -> cors.configurationSource(corsConfigurationSource));
        return http.build();
    }

    private void headers(HeadersConfigurer<HttpSecurity> headers) {
        headers
                .contentTypeOptions(withDefaults())
                .frameOptions(frame -> frame.deny())
                .xssProtection(xss -> xss.disable());
    }
}
