package com.example.tollschallengeapi.config.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.tollschallengeapi.domain.Usuario;
import org.springframework.beans.factory.annotation.Value;

@Service
public class TokenService {
	 	
		@Value("${api.security.token.secret}")
	    private String secret;
	    public String generateToken(Usuario usu){
	        try {
	            Algorithm algorithm = Algorithm.HMAC256(secret);

	            String token = JWT.create()
	                    .withIssuer("tools-challenge-api")
	                    .withSubject(usu.getEmail())
	                    .withExpiresAt(this.generateExpirationDate())
	                    .sign(algorithm);
	            return token;
	        } catch (JWTCreationException exception){
	            throw new RuntimeException("Error while authenticating");
	        }
	    }

	    public String validateToken(String token){
	        try {
	            Algorithm algorithm = Algorithm.HMAC256(secret);
	            return JWT.require(algorithm)
	                    .withIssuer("tools-challenge-api")
	                    .build()
	                    .verify(token)
	                    .getSubject();
	        } catch (JWTVerificationException exception) {
	            return null;
	        }
	    }

	    private Instant generateExpirationDate(){
	        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	        
	    }   
}
