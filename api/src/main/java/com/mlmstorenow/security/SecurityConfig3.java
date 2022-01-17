package com.mlmstorenow.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig3 extends WebSecurityConfigurerAdapter {

// @Override
// protected void configure(HttpSecurity http) throws Exception {

// http
// .cors().configurationSource(corsConfigurationSource());
// // your security config here
// http
// .authorizeRequests()
// // .antMatchers(HttpMethod.TRACE, "/**").denyAll()
// // .antMatchers("*").authenticated()
// .anyRequest().permitAll()
// .and().httpBasic()
// // .and().headers().frameOptions().disable()
// .and().csrf().disable()
// .headers()
// // the headers you want here. This solved all my CORS problems!
// .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin", "*"))
// .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Methods",
// "POST, GET"))
// .addHeaderWriter(new StaticHeadersWriter("Access-Control-Max-Age", "3600"))
// .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Credentials",
// "true"))
// .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Headers",
// "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization"));
// }

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
