package com.musicShare.entities;

import java.sql.Date;
import java.util.function.IntPredicate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    private float rating;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    private UserDetails originalPoster;

    public Review(String comment, float rating, UserDetails op) {
        this.originalPoster = op;
        this.comment = comment;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDetails getOriginalPoster() {
        return originalPoster;
    }

    public void setPlayListId(UserDetails op) {
        this.originalPoster = op;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new Date(System.currentTimeMillis());
        updatedAt = new Date(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date(System.currentTimeMillis());
    }

    // @ManyToOne
    // @JoinColumn(name = "user_id")
    // private UserDetails user;

    // @ManyToOne
    // @JoinColumn(name = "playlist_id")
    // private PlayList playList;

    // public void setPlayList(PlayList playList) {
    // this.playList = playList;
    // }

    // public Date getCreatedAt() {
    // return createdAt;
    // }

    // public UserDetails getUser() {
    // return user;
    // }

    // public PlayList getPlayList() {
    // return playList;
    // }

    // public void setPlayList(Object playList2) {
    // }

    // public void setUser(UserDetails user) {
    // this.user = user;
    // }

}
