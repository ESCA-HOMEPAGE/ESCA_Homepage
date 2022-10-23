package com.esca.escahp.auth.infra;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {
        if (HttpMethod.OPTIONS.matches(request.getMethod()) || process(request)) {
            return true;
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return false;
    }

    abstract boolean process(HttpServletRequest request);
}
