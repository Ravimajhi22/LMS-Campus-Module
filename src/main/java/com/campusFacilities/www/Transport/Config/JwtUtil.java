package com.campusFacilities.www.Transport.Config;

import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	    private static final String SECRET_KEY =
	            "my-super-secure-jwt-secret-key-my-super-secure";

	    private SecretKey getSigningKey() {
	        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	    }

	    private Claims extractAllClaims(String token) {
	        return Jwts.parserBuilder()
	                .setSigningKey(getSigningKey())
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
	    }

	    public boolean isTokenValid(String token) {
	        try {
	            extractAllClaims(token);
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	    }

	    public Long extractUserId(String token) {
	        Claims claims = extractAllClaims(token);
	        return claims.get("userId", Long.class);
	    }

	    @SuppressWarnings("unchecked")
	    public List<String> extractRoles(String token) {
	        Claims claims = extractAllClaims(token);
	        return (List<String>) claims.get("roles");
	    }

	    @SuppressWarnings("unchecked")
	    public List<String> extractPermissions(String token) {
	        Claims claims = extractAllClaims(token);
	        return (List<String>) claims.get("permissions");
	    }

	    public String extractUsername(String token) {
	        return extractAllClaims(token).getSubject();
	    }
	}


