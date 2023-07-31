package com.musicShare.musicShare.repositories;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.musicShare.entities.UserDetails;
import com.musicShare.repositories.UserDetailsRepository;

@DataJpaTest
public class UserDetailsTests {

    @Autowired
    private UserDetailsRepository repository;

    @Test
    public void testFindByEmail() {

        UserDetails Account1 = new UserDetails("Account1@test.com", "password1");
        UserDetails Account2 = new UserDetails("Account2@test.com", "password2");
        UserDetails Account3 = new UserDetails("Account3@test.com", "password3");

        System.out.println(Account1);
        repository.save(Account1);
        repository.save(Account2);
        repository.save(Account3);

        UserDetails Account = repository.findByEmail("Account1@test.com");
        assertEquals(Account.getEmail(), Account1.getEmail());
    }

}
