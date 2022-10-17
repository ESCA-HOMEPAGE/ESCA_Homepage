package com.esca.escahp.auth.config;

import com.esca.escahp.auth.exception.AuthExceptionSet;
import com.esca.escahp.exception.EscaException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String secretKey;
    private final long tokenValidMillisecond = 1000L * 60 * 60; // 1시간 토큰 유효

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String payload) {
        Claims claims = Jwts.claims().setSubject(payload);
        Date now = new Date();
        Date validity = new Date(now.getTime() + tokenValidMillisecond);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            String payload = token.split(" ")[1];
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(payload);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new EscaException(AuthExceptionSet.INVALID_TOKEN);
        }
    }
}
