package com.musicShare.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musicShare.entities.Song;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByArtist(String artist);

    List<Song> findBySongId(String songId);

}