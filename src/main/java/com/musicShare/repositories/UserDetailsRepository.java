package com.musicShare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musicShare.entities.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    UserDetails findByEmail(String email);
}