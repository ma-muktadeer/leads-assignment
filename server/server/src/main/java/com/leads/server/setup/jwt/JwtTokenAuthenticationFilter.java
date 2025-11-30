package com.leads.server.setup.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.leads.server.setup.principal.UserPrincipal;
import com.leads.server.setup.service.AuthService;
import com.leads.server.setup.service.UserPrincipalService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenAuthenticationFilter.class);
    private static final String JWT_TOKEN_PREFIX = "Bearer ";
    private static final String JWT_TOKEN_HEADER = "Authorization";

    private final AuthService authService;
    private final UserPrincipalService userPrincipalService;

    public JwtTokenAuthenticationFilter(AuthService authService, UserPrincipalService userPrincipalService) {
        super();
        this.authService = authService;
        this.userPrincipalService = userPrincipalService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        try {
            String token = resolveToken(request);
            if (token == null) {
                sendUnauthorizedResponse(response, "Access denied. Unauthorized request.",
                        HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            Claims claims = authService.getClaimsFromToken(token);
            String username = claims.getSubject();
            if (username == null) {
                chain.doFilter(request, response);
                return;
            }

            UserPrincipal userDetails = userPrincipalService.loadUserByUsername(username);
            if (userDetails == null) {
                chain.doFilter(request, response);
                return;
            }

            if (!authService.validateToken(claims, userDetails)) {
                sendUnauthorizedResponse(response, "Access denied. Unauthorized request.",
                        HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request, response);
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }
        chain.doFilter(request, response);
    }

    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().matches("/api/public/.*")
                || request.getServletPath().matches("/api/fake-store/.*");
    }

    private void sendUnauthorizedResponse(HttpServletResponse response, String message, int status) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(status);
        response.getWriter().write("{\"error\": \"" + message + "\"}");
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(JWT_TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(JWT_TOKEN_PREFIX)) {
            return bearerToken.substring(JWT_TOKEN_PREFIX.length());
        }
        return null;
    }

}
