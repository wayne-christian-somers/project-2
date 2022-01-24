package com.mlmstorenow.api.config;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebFilter;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@WebFilter("/*")
public class CorsFiltration {

	public CorsFilter corsFilter() {

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true); //
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		config.addAllowedOriginPattern("*");
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.stream(HttpMethod.values()).map(HttpMethod::name).collect(Collectors.toList()));
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);

	}
}
