package com.example.HelloDemo.service;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.HelloDemo.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	private final String SECRET_KEY = "8be74d1b020b8135a11153a21cf96f265015efc688e9238d87e1a525f0437a86";

	 @Value("${jwt.secret}")
	  private String secretKey;
	 
	public String extractUsername(String token) {
		return extractClaim(token , Claims:: getSubject);
	}
	
	public boolean isValid(String token, UserDetails user) {
		String username = extractUsername(token);
		return (username.equals(user.getUsername())) && !isTokenExpired(token);
	}
	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
public <T> T extractClaim(String token, Function<Claims, T> resolver) {
	Claims claims = extractAllClaims(token);
	return resolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts
				.parser()
				.verifyWith(getSigninKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	public String generateToken(User user) {
		String token = Jwts
				.builder()
				.subject(user.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+24*60*60*1000))
				.signWith(getSigninKey())
				.compact();
		
		return token;
	}
	
	private SecretKey getSigninKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);

		
	}
}


