package com.sparta.upschedulerv2.config;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

public class WebMvcConfig implements WebMvcConfigurer {
    private final ArgumentResolver argumentResolver;

    public WebMvcConfig(ArgumentResolver argumentResolver){
        this.argumentResolver = argumentResolver;

    }
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers){
        resolvers.add(argumentResolver);
    }
}
