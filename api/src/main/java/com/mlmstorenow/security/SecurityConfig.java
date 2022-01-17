package com.mlmstorenow.security;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractInterceptUrlConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration(proxyBeanMethods = false)
// @EnableWebSecurity
// @EnableWebMvc
// public class SecurityConfig extends WebSecurityConfigurerAdapter implements
// WebMvcConfigurer {

// @Override
// public void addCorsMappings(CorsRegistry registry) {

// registry.addMapping("*")
// .allowedOrigins("*")
// .allowedMethods("POST", "PUT", "DELETE", "GET", "PATCH")
// .allowedHeaders("*")
// .exposedHeaders("*")
// .allowCredentials(true).maxAge(3600);

// // Add more mappings...
// }

// @Override
// protected void configure(HttpSecurity http) throws Exception {
// http.csrf().disable();
// http
// .cors(Customizer.withDefaults());

// // http.cors().configurationSource(request -> new
// // CorsConfiguration().applyPermitDefaultValues());

// // REST is stateless
// // http.sessionManagement()
// // .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

// }

// // To enable CORS
// // @Bean
// // public CorsConfigurationSource corsConfigurationSource() {
// // final CorsConfiguration configuration = new CorsConfiguration();
// // configuration.applyPermitDefaultValues();
// // // configuration.setAllowedOrigins(List.of("https://www.yourdomain.com"));
// //
// // www - obligatory
// // // configuration.setAllowedOrigins(List.of("*")); // set access from all
// // // // domains
// // // configuration.setAllowedMethods(List.of("GET", "POST", "PUT",
// "DELETE"));
// // // configuration.setAllowCredentials(true);
// // // configuration.setAllowedHeaders(List.of("Authorization",
// "Cache-Control",
// // "Content-Type"));

// // final UrlBasedCorsConfigurationSource source = new
// // UrlBasedCorsConfigurationSource();
// // source.registerCorsConfiguration("/**", configuration);

// // return source;
// // }
// }