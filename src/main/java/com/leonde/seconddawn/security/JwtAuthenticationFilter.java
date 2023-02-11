package com.leonde.seconddawn.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private JwtProvider jwtProvider;

    public JwtAuthenticationFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) {
        // Extract the username and password from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate the credentials
        if (username == null || password == null) {
            throw new BadCredentialsException("Invalid request");
        }

        // Authenticate the user
        Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
        return getAuthenticationManager().authenticate(auth);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) {
        // Generate the JWT token
        String token = jwtProvider.generateToken(authResult);

        // Add the token to the response
        response.addHeader("Authorization", "Bearer " + token);
    }
}


