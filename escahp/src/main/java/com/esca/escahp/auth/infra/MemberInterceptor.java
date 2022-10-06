package com.esca.escahp.auth.infra;

import com.esca.escahp.auth.exception.AuthExceptionSet;
import com.esca.escahp.exception.EscaException;
import lombok.AllArgsConstructor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@AllArgsConstructor
public class MemberInterceptor extends AbstractInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    boolean process(HttpServletRequest request) {
        String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
        if (token != null && token.length() > 0) {
            return jwtTokenProvider.validateToken(token);
        }
        throw new EscaException(AuthExceptionSet.INVALID_TOKEN);
    }
}
