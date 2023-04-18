package com.musicShare.musicShare.services.entities;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Encryption {
    public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
