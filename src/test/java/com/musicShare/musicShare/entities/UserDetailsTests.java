package com.musicShare.musicShare.entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserDetailsTests {

    @Test
    public void testGettersAndSetters() {
        UserDetails userDetails = new UserDetails();
        userDetails.setId(1L);
        userDetails.setUsername("testuser");
        userDetails.setEmail("testuser@example.com");
        userDetails.setPassword("testpassword");
        userDetails.setEnabled(true);
        userDetails.hashPassword();
        userDetails.setTelNo("1234567890");

        assertEquals(1L, userDetails.getId().longValue());
        assertEquals("testuser", userDetails.getUsername());
        assertEquals("testuser@example.com", userDetails.getEmail());
        assertTrue(userDetails.confirmPassword("testpassword"));
        assertTrue(userDetails.isEnabled());
        assertEquals("1234567890", userDetails.getTelNo());
    }

    @Test
    public void testConstructor() {
        UserDetails userDetails = new UserDetails("testuser", "testuser@example.com", "testpassword");
        assertEquals("testuser", userDetails.getUsername());
        assertEquals("testuser@example.com", userDetails.getEmail());
        assertEquals("testpassword", userDetails.getPassword());
        assertFalse(userDetails.isEnabled());
    }

    @Test
    public void testPasswordEncryption() {
        String password = "testpassword";
        UserDetails userDetails = new UserDetails();
        userDetails.setPassword(password);
        userDetails.hashPassword();
        assertNotEquals(password, userDetails.getPassword());
        assertTrue(userDetails.confirmPassword(password));
    }

}
