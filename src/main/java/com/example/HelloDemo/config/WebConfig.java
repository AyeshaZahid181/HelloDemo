package com.example.HelloDemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.HelloDemo.interceptor.CustomHeaderInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final CustomHeaderInterceptor customHeaderInterceptor;

    @Autowired
    public WebConfig(CustomHeaderInterceptor customHeaderInterceptor) {
        this.customHeaderInterceptor = customHeaderInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customHeaderInterceptor).addPathPatterns("/register");
    }
}
