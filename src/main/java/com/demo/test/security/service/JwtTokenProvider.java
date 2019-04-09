package com.demo.test.security.service;

import com.demo.test.security.configuration.SecurityConfig;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
@Log4j2
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Autowired
    private SecurityConfig securityConfig;


    public String generateToken(Authentication authentication) {
        Date expiryDate = new Date(Long.MAX_VALUE);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("authorities", authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, securityConfig.getSecret())
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(securityConfig.getSecret())
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(securityConfig.getSecret()).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.debug("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.debug("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.debug("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.debug("JWT claims string is empty.");
        }
        return false;
    }
}