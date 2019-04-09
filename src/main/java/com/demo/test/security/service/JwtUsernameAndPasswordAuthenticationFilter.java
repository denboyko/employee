package com.demo.test.security.service;

import com.demo.test.security.configuration.SecurityConfig;
import com.demo.test.security.entity.Admin;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Log4j2
public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authManager;

    private final SecurityConfig securityConfig;

    private final JwtTokenProvider jwtTokenProvider;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authManager, SecurityConfig securityConfig, JwtTokenProvider jwtTokenProvider) {
        this.authManager = authManager;
        this.securityConfig = securityConfig;
        this.jwtTokenProvider = jwtTokenProvider;
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(securityConfig.getUri(), "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            Admin creds = new ObjectMapper().readValue(request.getInputStream(), Admin.class);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    creds.getUsername(), creds.getPassword(), Collections.emptyList());
            return authManager.authenticate(authToken);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        log.debug("Authentication Successful for user with name: " + auth.getName());
        response.addHeader("Authorization", jwtTokenProvider.generateToken(auth));
        response.setStatus(HttpServletResponse.SC_OK);
    }
}