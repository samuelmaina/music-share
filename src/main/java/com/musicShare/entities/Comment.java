package com.musicShare.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToMany(cascade = CascadeType.ALL)
    private User commentor;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> replies = new ArrayList<>();

    private int likes;
    private int shares;

    @OneToMany(cascade = CascadeType.ALL)
    private PlayList playlist;

    public Comment() {
        this.setComment("");
        this.setLikes(0);
        this.setShares(0);
    }

    public void setPlayList(PlayList playlist) {
        this.playlist = playlist;
    }

    public PlayList getPlayList() {
        return this.playlist;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getLikes() {
        return this.likes;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public int getShares() {
        return this.shares;
    }

    public void addReply(Comment reply) {
        this.replies.add(reply);
    }

    public List<Comment> getReplies() {
        return this.replies;
    }

    public void incrementLikes() {
        this.likes++;
    }

    public void decrementLikes() {
        if (this.likes > 0) {
            this.likes--;
        } else {
            throw new IllegalArgumentException("You don't have any likes");
        }
    }

    public void incrementShares() {
        this.shares++;
    }

    public Comment(String comment, float rating, User commentor) {
        this.setComment(comment);
        this.setCommentor(commentor);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCommentor(User commentor) {
        this.commentor = commentor;
    }

    public User getCommetor() {
        return this.commentor;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdateAt() {
        return updatedAt;
    }

}
