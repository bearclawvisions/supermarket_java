package com.bearclawvisions.api.helpers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {

    private final String secret = "super-secret-key-super-secret-key";
    private final long expirationTime = 86400000L;
    private static final String ISSUER = "supermarket_java";

    public String generateToken(UUID userId, String email, String role) throws IllegalArgumentException, JWTCreationException {
        long now = System.currentTimeMillis();
        Date issuedAt = new Date(now);
        Date expiresAt = new Date(now + expirationTime);

        return JWT.create()
                .withSubject(email)
                .withClaim("id", userId.toString())
                .withClaim("role", role)
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withIssuer(ISSUER)
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveSubject(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withIssuer(ISSUER)
                .build();

        DecodedJWT jwt = verifier.verify(token);
        return jwt.getSubject(); // Returns email
    }

    public DecodedJWT getDecodedToken(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withIssuer(ISSUER)
                .build();

        return verifier.verify(token);
    }

    public UUID getUserIdFromToken(String token) throws JWTVerificationException {
        DecodedJWT jwt = getDecodedToken(token);
        return UUID.fromString(jwt.getClaim("id").asString());
    }

    public String getRoleFromToken(String token) throws JWTVerificationException {
        DecodedJWT jwt = getDecodedToken(token);
        return jwt.getClaim("role").asString();
    }
}
