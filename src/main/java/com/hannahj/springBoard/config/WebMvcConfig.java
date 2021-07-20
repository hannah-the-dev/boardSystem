package com.hannahj.springBoard.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hannahj.springBoard.config.auth.LoginUserArgumentResolver;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    
    private final LoginUserArgumentResolver loginUserArgumentResolver;
    
//    @SuppressWarnings("unchecked")
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        SignInInterceptor signInInterceptor = new SignInInterceptor();
//        registry.addInterceptor(signInInterceptor)
//                .addPathPatterns(signInInterceptor.mustSignIn)
//                .excludePathPatterns();
//    }
    
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserArgumentResolver);
    }
}
