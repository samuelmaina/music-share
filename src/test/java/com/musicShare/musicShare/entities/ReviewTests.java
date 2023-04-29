package com.musicShare.musicShare.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.musicShare.musicShare.repositories.ReviewRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ReviewTests {

    @Autowired
    private ReviewRepository reviewRepository;

    private UserDetails userDetails;
    private PlayList playList;

    @BeforeEach
    public void setUp() {
        userDetails = new UserDetails("username", "password", "email");
        playList = new PlayList("title");
    }

    @Test
    public void givenReview_whenSave_thenGetOk() {
        Review review = new Review("This is a comment", 4.5f, userDetails);
        review.setPlayList(playList);
        Review savedReview = reviewRepository.save(review);

        assertThat(savedReview).isNotNull();
        assertThat(savedReview.getId()).isGreaterThan(0);
        assertThat(savedReview.getComment()).isEqualTo("This is a comment");
        assertThat(savedReview.getRating()).isEqualTo(4.5f);
        assertThat(savedReview.getOriginalPoster()).isEqualTo(userDetails);
        assertThat(savedReview.getCreatedAt()).isNotNull();
        assertThat(savedReview.getCreatedAt()).isNotNull();
        assertThat(savedReview.getUser()).isNull();
        assertThat(savedReview.getPlayList()).isEqualTo(playList);
    }

    @Test
    public void givenReview_whenUpdate_thenGetOk() {
        Review review = new Review("This is a comment", 4.5f, userDetails);
        review.setPlayList(playList);
        reviewRepository.save(review);

        Review updatedReview = reviewRepository.findById(review.getId()).orElse(null);
        updatedReview.setComment("This is an updated comment");
        updatedReview.setRating(3.0f);
        updatedReview.setOriginalPoster(new UserDetails("updated_username", "updated_password", "updated_email"));
        updatedReview.setCreatedAt(new Date(System.currentTimeMillis() - 100000));
        updatedReview.setUpdatedAt(new Date(System.currentTimeMillis()));
        updatedReview.setUser(userDetails);
        updatedReview.setPlayList(null);
        reviewRepository.save(updatedReview);

        Review checkReview = reviewRepository.findById(review.getId()).orElse(null);
        assertThat(checkReview).isNotNull();
        assertThat(checkReview.getId()).isEqualTo(review.getId());
        assertThat(checkReview.getComment()).isEqualTo("This is an updated comment");
        assertThat(checkReview.getRating()).isEqualTo(3.0f);
        assertThat(checkReview.getOriginalPoster().getUsername()).isEqualTo("updated_username");
        assertThat(checkReview.getOriginalPoster().getPassword()).isEqualTo("updated_password");
        assertThat(checkReview.getOriginalPoster().getEmail()).isEqualTo("updated_email");
        assertThat(checkReview.getCreatedAt()).isEqualTo(new Date(System.currentTimeMillis() - 100000));
        assertThat(checkReview.getCreatedAt()).isEqualTo(new Date(System.currentTimeMillis()));
        assertThat(checkReview.getUser()).isEqualTo(userDetails);
        assertThat(checkReview.getPlayList()).isNull();
    }

    @Test
    public void givenReview_whenDelete_thenGetOk() {
        Review review = new Review("This is a comment", 4.5f, userDetails);
        review.setPlayList(playList);
        reviewRepository.save(review);

        reviewRepository.delete(review);

        assertThat(reviewRepository.findById(review.getId())).isEmpty();
    }
}
