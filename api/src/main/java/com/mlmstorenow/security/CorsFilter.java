package com.mlmstorenow.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

// @Component
// @WebFilter("/*")
// public class CorsFilter extends OncePerRequestFilter {

// @Override
// protected void doFilterInternal(HttpServletRequest req, HttpServletResponse
// resp, FilterChain chain)
// throws ServletException, IOException {

// resp.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
// resp.setHeader("Access-Control-Allow-Credentials", "true");
// resp.setHeader("Access-Control-Allow-Methods",
// "GET,HEAD,OPTIONS,POST,PUT,DELETE,PATCH");
// resp.setHeader("Access-Control-Allow-Headers",
// "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type,
// Access-Control-Request-Method, Access-Control-Request-Headers");
// if ("OPTIONS".equals(req.getMethod())) {
// resp.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
// resp.setStatus(HttpServletResponse.SC_OK);
// } else {
// chain.doFilter(req, resp);
// }
// chain.doFilter(req, resp);
// }

// }
