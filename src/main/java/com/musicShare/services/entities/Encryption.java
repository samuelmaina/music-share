package com.musicShare.services.entities;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class Encryption {
    public static String hash(String password) {
        throwIfNull(password, "Password");
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean verifyHash(String password, String hash) {
        throwIfNull(password, "Password");
        throwIfNull(hash, "Hash");
        return BCrypt.checkpw(password, hash);
    }

    private static void throwIfNull(String arg, String fieldName){
        if (arg == null) {
            throw new NullPointerException(fieldName + " cannot be null.");
        }
    }
}
