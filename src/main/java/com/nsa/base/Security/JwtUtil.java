
// first step

package com.nsa.base.Security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	
	@Value("${app.jwt.secret}") private String secret;
	  private final long EXP_MS = 1000L * 60 * 60 * 12; // 12h

	  private SecretKey getSecretKey() {
		  byte[] keyBytes = Decoders.BASE64.decode(secret);
		  return Keys.hmacShaKeyFor(keyBytes);
	  }
	  
	  
	  public String generate(String username) {
	    return Jwts.builder()
	    		.subject(username)
	    		.issuedAt(new Date())
	    		.expiration(new Date(System.currentTimeMillis()+EXP_MS))
	    		.signWith(getSecretKey())
	    		.compact();
//	      .setSubject(username)
//	      .setIssuedAt(new Date())
//	      .setExpiration(new Date(System.currentTimeMillis() + EXP_MS))
//	      .signWith(SignatureAlgorithm.HS256, secret)
//	      .compact();
	  }

	  public String username(String token) {
	    return Jwts.parser()
	    		.verifyWith(getSecretKey())
	    		.build()
	    		.parseSignedClaims(token)
	    		.getPayload()
	    		.getSubject();
	    		
	    
	    		
	    		
	    	
	  }

	  public boolean valid(String token) {
	    try { 
	    	Jwts.parser()
	    .verifyWith(getSecretKey())
	    .build()
	    .parseSignedClaims(token);
	    return true;
	    }catch (JwtException e) {
	    	return false; 
	    	}
	  }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
}
