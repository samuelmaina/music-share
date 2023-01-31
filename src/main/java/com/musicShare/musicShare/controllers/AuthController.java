package com.musicShare.musicShare.controllers;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.musicShare.musicShare.entities.UserDetails;
import com.musicShare.musicShare.repositories.UserDetailsRepository;

@RestController
public class AuthController {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDetails user) {
        if (userDetailsRepository.findByEmail(user.getEmail()) != null) {
            return new ResponseEntity<>("Email already exists",
                    HttpStatus.BAD_REQUEST);
        }

        user.setPassword(hash(user.getPassword()));
        user.setEnabled(true);
        userDetailsRepository.save(user);

        return new ResponseEntity<>("Account created Successfully.", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDetails user) {
        UserDetails foundUser = userDetailsRepository.findByEmail(user.getEmail());
        String errorMessage = "Invalid email or password";

        if (foundUser == null) {
            return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
        }

        if (!verifyHash(user.getPassword(),
                foundUser.getPassword())) {
            return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(foundUser, HttpStatus.OK);
        }
    }
}
