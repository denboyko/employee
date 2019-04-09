package com.demo.test.security.handler;

import com.demo.test.security.service.JwtTokenProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Log4j2
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.debug("Authentication Successful for user with name: " + authentication.getName());
        response.setHeader("Authorization", jwtTokenProvider.generateToken(authentication));
        response.setStatus(HttpServletResponse.SC_OK);
    }
}