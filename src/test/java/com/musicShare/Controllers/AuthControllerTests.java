package com.musicShare.musicShare.Controllers;



import com.musicShare.repositories.UserDetailsRepository;
import com.musicShare.services.controllers.JWTAuthenticationFilter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicShare.controllers.AuthController;
import com.musicShare.entities.UserDetails;

class AuthControllerTests {
    private MockMvc mockMvc;

    private final String baseUrl = "/auth";

    @Mock
    private UserDetailsRepository userDetailsRepository;

    @Mock
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    void testSignupWithValidUser() throws Exception {
        UserDetails user = new UserDetails();
        user.setEmail("user@example.com");
        user.setPassword("password");

        when(userDetailsRepository.findByEmail(user.getEmail())).thenReturn(null);
        when(userDetailsRepository.save(any(UserDetails.class))).thenReturn(user);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);

        mockMvc.perform(post(baseUrl + "/signup").contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().string("Account created Successfully."));

    }

    @Test
    void testSignupWithExistingEmail() throws Exception {
        UserDetails user = new UserDetails();
        user.setEmail("user@example.com");
        user.setPassword("password");

        when(userDetailsRepository.findByEmail(user.getEmail())).thenReturn(user);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);

        mockMvc.perform(post(baseUrl + "/signup").contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Email already exists"));
    }

    @Test
    void testLoginWithValidUser() throws Exception {
        UserDetails user = new UserDetails();
        user.setEmail("user@example.com");
        user.setPassword("password");

        UserDetails hashedUser = new UserDetails();
        hashedUser.setEmail("user@example.com");
        hashedUser.setPassword("password");
        hashedUser.hashPassword();

        when(userDetailsRepository.findByEmail(hashedUser.getEmail())).thenReturn(hashedUser);
        when(jwtAuthenticationFilter.generateToken(hashedUser)).thenReturn("token");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        mockMvc.perform(post(baseUrl + "/login").contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("token"));
    }

    @Test
    void testLoginWithInvalidEmail() throws Exception {
        UserDetails user = new UserDetails();
        user.setEmail("user@example.com");
        user.setPassword("password");
        user.setEnabled(true);

        when(userDetailsRepository.findByEmail(user.getEmail())).thenReturn(null);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);

        mockMvc.perform(post(baseUrl + "/login").contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid email or password"));
    }

    @Test
    void testLoginWithInvalidPassword() throws Exception {
        UserDetails user = new UserDetails();
        user.setEmail("user@example.com");
        user.setPassword("password");
        user.setEnabled(true);

        when(userDetailsRepository.findByEmail(user.getPassword())).thenReturn(null);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);

        mockMvc.perform(post(baseUrl + "/login").contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid email or password"));
    }
}
