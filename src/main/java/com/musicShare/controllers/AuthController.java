package com.musicShare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musicShare.entities.UserDetails;
import com.musicShare.repositories.UserDetailsRepository;
import com.musicShare.services.controllers.JWTAuthenticationFilter;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDetails user) {
        if (userDetailsRepository.findByEmail(user.getEmail()) != null) {
            return new ResponseEntity<>("Email already exists",
                    HttpStatus.BAD_REQUEST);
        }
        user.hashPassword();
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

        if (!foundUser.confirmPassword(user.getPassword())) {
            return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
        }

        // user authenticated successfully, generate JWT token
        String token = jwtAuthenticationFilter.generateToken(foundUser);

        // return token as response
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
