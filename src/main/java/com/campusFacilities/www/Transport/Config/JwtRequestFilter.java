package com.campusFacilities.www.Transport.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	    @Autowired
	    private JwtUtil jwtUtil;

	    @Override
	    protected void doFilterInternal(HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    FilterChain filterChain)
	            throws ServletException, IOException, java.io.IOException {

	        String authHeader = request.getHeader("Authorization");

	        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing JWT Token");
	            return;
	        }

	        String token = authHeader.substring(7);

	        if (!jwtUtil.isTokenValid(token)) {
	            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT Token");
	            return;
	        }

	        // Attach token data to request
	        request.setAttribute("userId", jwtUtil.extractUserId(token));
	        request.setAttribute("roles", jwtUtil.extractRoles(token));
	        request.setAttribute("permissions", jwtUtil.extractPermissions(token));

	        filterChain.doFilter(request, response);
	    }
	}

