package com.leads.server.setup.jwt;

import java.nio.charset.StandardCharsets;
import io.jsonwebtoken.security.SignatureException;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.leads.server.setup.principal.UserPrincipal;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider {
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

	private static final String AUTHORITIES_KEY = "roles";

	private final JwtProperties jwtProperties;

	private SecretKey secretKey;

	public JwtTokenProvider(JwtProperties jwtProperties) {
		super();
		this.jwtProperties = jwtProperties;
	}

	@PostConstruct
	public void init() {
		String secret = Base64.getEncoder().encodeToString(this.jwtProperties.getSecretKey().getBytes());
		this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}

	public String createToken(UserPrincipal userPrincipal) {
		try {
			String username = userPrincipal.getUsername();
			Collection<? extends GrantedAuthority> authorities = userPrincipal.getAuthorities();
			ClaimsBuilder claimsBuilder = Jwts.claims().subject(username);
			claimsBuilder.add("id", userPrincipal.getId());

			if (authorities != null && !authorities.isEmpty()) {
				String roles = authorities.stream()
						.map(GrantedAuthority::getAuthority)
						.collect(Collectors.joining(","));
				claimsBuilder.add(AUTHORITIES_KEY, roles);
			}

			Claims claims = claimsBuilder.build();

			Date now = new Date();
			Date expiry = new Date(now.getTime() + jwtProperties.getValidityInMs());

			return Jwts.builder()
					.id(userPrincipal.getId().toString())
					.claims(claims)
					.issuedAt(now)
					.expiration(expiry)
					.signWith(secretKey)
					.compact();

		} catch (Exception e) {
			logger.error("Error while creating token", e);
			return "Authentication Failed";
		}
	}

	public boolean validateToken(String token) {
		try {
			getClaimsFromToken(token);
			return true;
		} catch (Exception e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
			return false;
		}
	}

	public Claims getClaimsFromToken(String token) {
		Claims claims = null;
		try {
			claims = Jwts
					.parser()
					.verifyWith(secretKey)
					.build()
					.parseSignedClaims(token)
					.getPayload();

		} catch (ExpiredJwtException e) {
			logger.error("Token expired: {}", e.getMessage());
			throw e;
		} catch (SignatureException e) {
			logger.error("Invalid token signature: {}", e.getMessage());
			throw new JwtException("Invalid token.");
		} catch (MalformedJwtException e) {
			logger.error("Malformed token: {}", e.getMessage());
			throw new JwtException("Invalid token.");
		} catch (Exception e) {
			logger.error("Token parsing error: {}", e.getMessage());
			throw new JwtException("Invalid token.");
		}
		return claims;
	}

	public boolean validateUsername(Claims claims, UserPrincipal userPrincipal) {
		HttpServletRequest request = null;
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if (requestAttributes != null) {
			request = requestAttributes.getRequest();
		}
		assert request != null;
		Long tokenUserId = claims.get("id", Long.class);
		if (tokenUserId == null || !tokenUserId.equals(userPrincipal.getId())) {
			return false;
		}
		String tokenDeviceFingerprint = claims.get("device", String.class);
		if (!claims.getSubject().equals(userPrincipal.getUsername())
				|| !StringUtils.hasLength(tokenDeviceFingerprint))
			return false;
		return true;
	}
}
