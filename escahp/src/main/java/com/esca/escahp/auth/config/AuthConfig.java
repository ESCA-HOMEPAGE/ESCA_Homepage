package com.esca.escahp.auth.config;

import com.esca.escahp.auth.infra.MemberInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@AllArgsConstructor
public class AuthConfig implements WebMvcConfigurer {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> addPatterns = List.of("/user/**", "/schedules/**", "/free/**", "/study/**", "/gallery/**", "/keeper/**", "/notice/**");
        registry.addInterceptor(new MemberInterceptor(jwtTokenProvider))
                .addPathPatterns(addPatterns);
    }
}
