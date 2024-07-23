package com.example.HelloDemo.interceptor;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomHeaderInterceptor implements HandlerInterceptor {

    private static final String REGISTRATION_SECRET_HEADER = "HelloDemo1";
    private static final String REGISTRATION_SECRET_VALUE = "Pass1234"; // Replace with your secret value

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("/register".equals(request.getRequestURI())) {
            String secretHeader = request.getHeader(REGISTRATION_SECRET_HEADER);
            if (secretHeader == null || !REGISTRATION_SECRET_VALUE.equals(secretHeader)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return false;
            }
        }
        return true;
    }
}
