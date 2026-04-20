package com.jspider.insurance_policy_management.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	  private final String SECRET = "mysecretkeymysecretkeymysecretkey123"; // 32+ chars
	  private final long EXPIRATION = 1000 * 60 * 60; // 1 hour
	  private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

	    // ✅ Generate Token
	    public String generateToken(String email) {
	        return Jwts.builder()
	                .setSubject(email)
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
	                .signWith(key, SignatureAlgorithm.HS256)
	                .compact();
	    }

	    // ✅ Extract Email
	    public String extractEmail(String token) {
	        return extractAllClaims(token).getSubject();
	    }

	    // ✅ Validate Token
	    public boolean validateToken(String token, String username) {
	        final String email = extractEmail(token);
	        return (email.equals(username) && !isTokenExpired(token));
	    }

	    // 🔍 Check Expiry
	    private boolean isTokenExpired(String token) {
	        return extractAllClaims(token).getExpiration().before(new Date());
	    }

	    // 🔍 Extract Claims
	    private Claims extractAllClaims(String token) {
	        return Jwts.parser()
	                .setSigningKey(key)
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
	    }
}
