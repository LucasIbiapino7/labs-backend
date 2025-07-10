package com.lab.backend.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ResourceServerConfig {

    @Bean
    JwtAuthenticationConverter jwtAuthConverter() {
        var granted = new JwtGrantedAuthoritiesConverter();
        granted.setAuthoritiesClaimName("authorities");
        granted.setAuthorityPrefix("");

        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(granted);
        return converter;
    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**", "/r/**", "/error").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/profile/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/laboratorio").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/laboratorio/*/summary").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/laboratorio/*/members").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/posts/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/evento/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/evento/*/next").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/material/*").permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth ->
                        oauth.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter())));
        return http.build();
    }

    @Bean
    @Profile("test")
    @Order(1)
    public SecurityFilterChain h2ConsoleSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher(PathRequest.toH2Console())
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions().disable())
                .authorizeHttpRequests(a -> a.anyRequest().permitAll());
        return http.build();
    }
}
