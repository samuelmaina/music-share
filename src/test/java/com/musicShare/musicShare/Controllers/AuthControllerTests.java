package com.musicShare.musicShare.Controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.musicShare.musicShare.controllers.AuthController;
import com.musicShare.musicShare.entities.UserDetails;
import com.musicShare.musicShare.repositories.UserDetailsRepository;

@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTests {

    @Mock
    private UserDetailsRepository userDetailsRepository;

    @InjectMocks
    private AuthController authController;
    private UserDetails user;

    private String testEmail = "testUserDetails@test.com";

    @Before
    public void setUp() {
        user = new UserDetails();
        user.setEmail(testEmail);
        user.setPassword("password");
    }

@Test
public void signup_existingUserDetailsname_returnsBadRequest() {
when(userDetailsRepository.findByEmail(testEmail)).thenReturn(new
UserDetails(testEmail,"password"));

ResponseEntity<?> response = authController.signup(user);

assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
assertEquals("Email already exists", response.getBody());
}

@Test
public void signup_newUserDetails_returnsCreated() {
when(userDetailsRepository.findByEmail(testEmail)).thenReturn(null);
when(userDetailsRepository.save(any(UserDetails.class))).thenReturn(new UserDetails());

ResponseEntity<?> response = authController.signup(user);

assertEquals(HttpStatus.CREATED, response.getStatusCode());
assertEquals("Account created Successfully.", response.getBody());
}

@Test
public void login_invalidUserDetailsname_returnsUnauthorized() {
when(userDetailsRepository.findByEmail(testEmail)).thenReturn(null);

ResponseEntity<?> response = authController.login(user);

assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
assertEquals("Invalid email or password", response.getBody());
}

    @Test
    public void login_invalidPassword_returnsUnauthorized() {
        UserDetails foundUserDetails = new UserDetails();
        foundUserDetails.setPassword(authController.hash("incorrectPassword"));
        when(userDetailsRepository.findByEmail(testEmail)).thenReturn(foundUserDetails);

        ResponseEntity<?> response = authController.login(user);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid email or password", response.getBody());
    }

}