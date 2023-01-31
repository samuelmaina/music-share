package com.musicShare.musicShare.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String artist;
    private Long views;
    @Column(unique = true)
    private String songId;

    public Song(String name, String artist, String songId) {
        this.setName(name);
        this.setArtist(artist);
        this.setSongId(songId);
        this.setViews(0L);
    }

    public Song() {
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongId() {
        return this.songId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

}
