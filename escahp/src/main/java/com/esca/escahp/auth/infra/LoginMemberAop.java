package com.esca.escahp.auth.infra;

import com.esca.escahp.auth.config.JwtTokenProvider;
import com.esca.escahp.auth.domain.LoginMember;
import com.esca.escahp.auth.exception.AuthExceptionSet;
import com.esca.escahp.exception.EscaException;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Aspect
@Component
@AllArgsConstructor
public class LoginMemberAop {
    private final JwtTokenProvider jwtTokenProvider;

    @Pointcut("execution(* com.esca.escahp..*.*(..))")
    private void cut(){}

    @Before("cut() && @annotation(auth)")
    public void before(JoinPoint joinPoint, LoginMember auth) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String header = request.getHeader("Authorization");
        String jwtUserId = jwtTokenProvider.extractId(header);
        Map<String, String> params = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String pathUserId = params.get("userId");

        if(!jwtUserId.equals(pathUserId)) {
            throw new EscaException(AuthExceptionSet.INVALID_AUTHORIZATION);
        }
    }
}
