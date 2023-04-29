package com.musicShare.musicShare.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musicShare.musicShare.entities.PlayList;
import com.musicShare.musicShare.entities.UserDetails;

public interface PlayListRepository extends JpaRepository<PlayList, Long> {
    List<PlayList> findByName(String name);

    List<PlayList> findByUser(UserDetails user);
}