package com.campusFacilities.www.Transport.Config;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null
                && authHeader.startsWith("Bearer ")
                && SecurityContextHolder.getContext().getAuthentication() == null) {
        	 String token = authHeader
        	            .replace("Bearer", "")
        	            .trim()
        	            .replaceAll("\\s+", "");


            try {
               
            	Keys.hmacShaKeyFor(jwtSecret.getBytes())

                ;

                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(
                                Keys.hmacShaKeyFor(jwtSecret.getBytes())
                        )
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                @SuppressWarnings("unchecked")
                List<String> roles = claims.get("roles", List.class);

                @SuppressWarnings("unchecked")
                List<String> permissions = claims.get("permissions", List.class);

                List<SimpleGrantedAuthority> authorities = new ArrayList<>();

                if (roles != null) {
                    roles.forEach(role ->
                        authorities.add(new SimpleGrantedAuthority(role))
                    );
                }

                if (permissions != null) {
                    permissions.forEach(p ->
                        authorities.add(new SimpleGrantedAuthority(p))
                    );
                }

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                claims.getSubject(),
                                null,
                                authorities
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);

            } catch (Exception e) {
            	e.printStackTrace();
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }
}
