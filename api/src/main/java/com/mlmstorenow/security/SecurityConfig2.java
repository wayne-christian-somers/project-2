package com.mlmstorenow.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig2 extends WebSecurityConfigurerAdapter {

// private static final List<String> list = null;

// @Override
// protected void configure(HttpSecurity http) throws Exception {
// // other http security config
// http.cors().configurationSource(corsConfigurationSource());
// }

// // This can be customized as required
// CorsConfigurationSource corsConfigurationSource() {
// CorsConfiguration configuration = new CorsConfiguration();
// List<String> allowOrigins = Arrays.asList("*");
// configuration.setAllowedOrigins(allowOrigins);
// configuration.setAllowedMethods(List.of("*"));
// configuration.setAllowedHeaders(List.of("*"));
// // in case authentication is enabled this flag MUST be set, otherwise CORS
// // requests will fail
// configuration.setAllowCredentials(true);
// UrlBasedCorsConfigurationSource source = new
// UrlBasedCorsConfigurationSource();
// source.registerCorsConfiguration("/**", configuration);
// return source;
// }
// }