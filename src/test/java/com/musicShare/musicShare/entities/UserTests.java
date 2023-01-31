package com.musicShare.musicShare.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UserTests {
    @Test
    public void testGetId() {
        User user = new User();
        user.setId(1L);
        assertEquals(1L, user.getId().longValue());
    }

    @Test
    public void testSetId() {
        User user = new User();
        user.setId(1L);
        assertEquals(1L, user.getId().longValue());
    }

    @Test
    public void testGetUsername() {
        User user = new User();
        String email = "testuser@user.com";
        user.setEmail(email);
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testSetUsername() {
        User user = new User();
        String email = "testuser@user.com";
        user.setEmail(email);
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testGetPassword() {
        User user = new User();
        user.setPassword("password123");
        assertEquals("password123", user.getPassword());
    }

    @Test
    public void testSetPassword() {
        User user = new User();
        user.setPassword("password123");
        assertEquals("password123", user.getPassword());
    }

    @Test
    public void testIsEnabled() {
        User user = new User();
        user.setEnabled(true);
        assertTrue(user.isEnabled());
    }

    @Test
    public void testSetEnabled() {
        User user = new User();
        user.setEnabled(true);
        assertTrue(user.isEnabled());
    }
}