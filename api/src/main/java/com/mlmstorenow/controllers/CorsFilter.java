package com.mlmstorenow.controllers;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Component
// @WebFilter("/*") // Our frontend Angular Application will be running on port
// 4200 and making
// // request to our localhost at port 5000
// public class CorsFilter extends OncePerRequestFilter {

// public WebMvcConfigurer corsConfigurer() {
// return new WebMvcConfigurer() {
// @Override
// public void addCorsMappings(CorsRegistry registry) {
// registry.addMapping("/greeting-javaconfig").allowedOrigins("http://localhost:8080");
// }
// };
// }

// @Override
// protected void doFilterInternal(HttpServletRequest request,
// HttpServletResponse response, FilterChain filterChain)
// throws ServletException, IOException {
// // TODO Auto-generated method stub

// }

// }