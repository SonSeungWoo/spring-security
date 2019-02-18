package com.github.ssw.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 비인증 공통 처리
 *
 * Created by Leo.
 * User: ssw
 * Date: 2019-02-12
 * Time: 11:13
 */
@Configuration
public class UnauthorizedAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        String result = String.format("{\"code\":\"%s\",\"message\": \"%s\"}", HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=utf-8");
        response.getOutputStream().println(result);
    }
}
