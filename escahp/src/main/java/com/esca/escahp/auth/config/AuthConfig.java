package com.esca.escahp.auth.config;

import com.esca.escahp.auth.infra.JwtTokenProvider;
import com.esca.escahp.auth.infra.MemberInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class AuthConfig implements WebMvcConfigurer {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MemberInterceptor(jwtTokenProvider))
                .addPathPatterns("/api/**"); // todo 로그인 api 개발 후 exclude pattern 추가 및 test 리팩토링
    }
}
