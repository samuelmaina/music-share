package com.musicShare.musicShare.services.entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class EncryptionTests {
    @Test
    public void testHash() {
        String password = "mypassword";
        String hash = Encryption.hash(password);
        assertNotNull(hash);
        assertTrue(hash instanceof String);
        assertTrue(hash.length() >= 60);
    }

    @Test
    public void testVerifyHashWithValidPassword() {
        String password = "mypassword";
        String hash = Encryption.hash(password);
        assertTrue(Encryption.verifyHash(password, hash));
    }

    @Test
    public void testVerifyHashWithInvalidPassword() {
        String password = "mypassword";
        String hash = Encryption.hash(password);
        assertFalse(Encryption.verifyHash("invalidpassword", hash));
    }

    @Test(expected = NullPointerException.class)
    public void testVerifyHashWithNullPassword() {
        String hash = Encryption.hash("mypassword");
        Encryption.verifyHash(null, hash);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testVerifyHashWithNullHash() {
        Encryption.verifyHash("mypassword", null);
    }

    @Test
    public void testHashWithEmptyPassword() {
        String password = "";
        String hash = Encryption.hash(password);
        assertNotNull(hash);
    }

    @Test
    public void testVerifyHashWithEmptyPassword() {
        String password = "";
        String hash = Encryption.hash(password);
        assertTrue(Encryption.verifyHash(password, hash));
    }
}
