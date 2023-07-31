package com.musicShare.musicShare.services.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.test.util.ReflectionTestUtils;

import com.musicShare.services.controllers.JWTAuthenticationFilter;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class JWTAuthenticationFilterTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Before
    public void setup() {
        jwtAuthenticationFilter = new JWTAuthenticationFilter(authenticationManager, userDetailsService);
        ReflectionTestUtils.setField(jwtAuthenticationFilter, "jwtSecret", "secret");
        ReflectionTestUtils.setField(jwtAuthenticationFilter, "jwtExpirationInHours", 1L);
    }

    @Test
    public void testDoFilterInternal_WithValidToken_ShouldAuthenticateUser() throws ServletException, IOException {

        UserDetails userDetails = new User("username", "password", new ArrayList<>());
        String token = jwtAuthenticationFilter.generateToken(userDetails);
        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        Mockito.when(userDetailsService.loadUserByUsername("username")).thenReturn(userDetails);
        Mockito.when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assertNotNull(authentication);
        assertEquals(userDetails.getUsername(), authentication.getName());
    }

    @Test
    public void testDoFilterInternal_WithInvalidToken_ShouldNotAuthenticateUser() throws ServletException, IOException {

        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer invalid-token");
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assertNull(authentication);
    }

    @Test
    public void testDoFilterInternal_WithNoToken_ShouldNotAuthenticateUser() throws ServletException, IOException {

        Mockito.when(request.getHeader("Authorization")).thenReturn(null);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assertNull(authentication);
    }

    @Test
    public void testDoFilterInternal_WithExpiredToken_ShouldNotAuthenticateUser() throws ServletException, IOException {

        UserDetails userDetails = new User("testUser", "testPassword", Collections.emptyList());
        String token = jwtAuthenticationFilter.generateToken(userDetails);
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() - 1000); // Set expiration date to a past time
        String expiredToken = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, "secret")
                .compact();
        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer " + expiredToken);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assertNull(authentication);
    }
}
