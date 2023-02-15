package com.musicShare.musicShare.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JWTAuthenticationFilterTests {

    private final UserDetailsService userDetailsService = mock(UserDetailsService.class);
    private final JWTAuthenticationFilter filter = new JWTAuthenticationFilter(mock(AuthenticationManager.class),
            userDetailsService);

    private final UserDetails userDetails = User.withUsername("user1").password("password")
            .authorities(new ArrayList<>()).build();
    private final String jwtSecret = "jwtSecret";
    private final long jwtExpiration = 3600_000; // 1 hour

    @Test
    public void testDoFilterInternalWithValidToken() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        String token = JWTAuthenticationFilter.generateToken(userDetails, jwtSecret, jwtExpiration);
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

        doNothing().when(filterChain).doFilter(any(HttpServletRequest.class), any(HttpServletResponse.class));

        ReflectionTestUtils.setField(filter, "jwtSecret", jwtSecret);

        filter.doFilterInternal(request, response, filterChain);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assertEquals(userDetails.getUsername(), authentication.getName());
        assertEquals(userDetails.getAuthorities(), authentication.getAuthorities());
    }

    @Test
    public void testDoFilterInternalWithInvalidToken() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        String token = "invalidToken";
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

        doNothing().when(filterChain).doFilter(any(HttpServletRequest.class), any(HttpServletResponse.class));

        ReflectionTestUtils.setField(filter, "jwtSecret", jwtSecret);

        filter.doFilterInternal(request, response, filterChain);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assertEquals(null, authentication);
    }

    @Test
    public void testGetAuthentication() {
        String token = JWTAuthenticationFilter.generateToken(userDetails, jwtSecret, jwtExpiration);

        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

        Authentication authentication = filter.getAuthentication(token);

        assertEquals(userDetails.getUsername(), authentication.getName());
        assertEquals(userDetails.getAuthorities(), authentication.getAuthorities());
    }

    @Test
    public void testGenerateToken() {
        String token = JWTAuthenticationFilter.generateToken(userDetails, jwtSecret, jwtExpiration);

        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

        assertEquals(userDetails.getUsername(), claims.getSubject());

        Date expirationDate = claims.getExpiration();
        long expirationTime = expirationDate.getTime() - claims.getIssuedAt().getTime();
        assertEquals(jwtExpiration, expirationTime);
    }
}
