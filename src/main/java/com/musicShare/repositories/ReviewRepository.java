package com.musicShare.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musicShare.entities.Review;
import com.musicShare.entities.Song;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Song> findByPlaylistId(long playListId);

    List<Song> findByUserId(long songId);
}