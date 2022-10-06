package com.esca.escahp.auth.infra;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public boolean validateToken(String token) {
        return true;
    }
}
