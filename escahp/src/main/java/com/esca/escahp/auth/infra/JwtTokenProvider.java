package com.esca.escahp.auth.infra;

import com.esca.escahp.auth.exception.AuthExceptionSet;
import com.esca.escahp.exception.EscaException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private final String secretKey = "secret"; // todo 환경 변수로 변경 예정

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new EscaException(AuthExceptionSet.INVALID_TOKEN);
        }
    }
}
