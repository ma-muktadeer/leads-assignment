package com.leads.server.setup.service;

import com.leads.server.setup.principal.UserPrincipal;

import io.jsonwebtoken.Claims;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.leads.server.setup.jwt.JwtTokenProvider;

@Service
public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public Authentication authenticateUser(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            return authentication;
            // return authenticationManager.authenticate(auth);
        } catch (Exception e) {
            logger.error("Authentication failed", e);
            throw new BadCredentialsException("Authentication failed", e);
        }
    }

    public String generateToken(UserPrincipal userPrincipal) {
        return jwtTokenProvider.createToken(userPrincipal);
    }

    public Claims getClaimsFromToken(String token) {
        return jwtTokenProvider.getClaimsFromToken(token);
    }

    public boolean validateToken(Claims claims, UserPrincipal userPrincipal) {
        return jwtTokenProvider.validateUsername(claims, userPrincipal);
    }

}
