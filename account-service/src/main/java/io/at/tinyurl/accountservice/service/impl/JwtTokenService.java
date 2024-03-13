package io.at.tinyurl.accountservice.service.impl;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.at.tinyurl.accountservice.dto.LoginResponse;
import io.at.tinyurl.accountservice.model.Account;
import io.at.tinyurl.accountservice.service.TokenService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtTokenService implements TokenService {

    @Value("${security.jwt.secret}")
    private String jwtSecret;

    @Value("${security.jwt.expiration}")
    private int jwtExpirationMs;

    @Override
    public String getUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(getKey()).build()
               .parseClaimsJws(token).getBody().getSubject();
    }

    @Override
    public LoginResponse generateToken(Account account) {
        Date expiresAt = new Date((new Date()).getTime() + jwtExpirationMs);
        String token = Jwts.builder()
            .setSubject((account.getUsername()))
            .setIssuedAt(new Date())
            .setExpiration(expiresAt)
            .setClaims(Map.of("roles", account.getRoles()))
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact();
        return new LoginResponse(token, expiresAt);
    }

    @Override
    public boolean validate(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getKey()).build().parse(token);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
    
    private Key getKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

}
