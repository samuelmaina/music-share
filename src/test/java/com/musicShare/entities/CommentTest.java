package com.musicShare.entities;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CommentTest {

    @Mock
    private User user;

    @Mock
    private PlayList mockPlayList;

    private Comment comment;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        comment = new Comment();
    }

    @Test
    public void getId() {
        Long expectedId = 1L;
        System.out.println(comment.getComment());
        comment.setId(expectedId);
        assertEquals(expectedId, comment.getId());
    }

    @Test
    public void getCommentorTest() {
        comment.setCommentor(user);
        assertEquals(user, comment.getCommetor());
    }

    @Test
    public void setComment() {
        String testComment = "Test comment";
        comment.setComment(testComment);
        assertEquals(testComment, comment.getComment());
    }

    @Test
    public void setPlayList() {
        comment.setPlayList(mockPlayList);
        assertEquals(mockPlayList, comment.getPlayList());
    }

    @Test
    public void testAddReply() {
        Comment reply = new Comment();
        comment.addReply(reply);
        List<Comment> replies = comment.getReplies();
        assertEquals(1, replies.size());
        assertEquals(reply, replies.get(0));
    }

    @Test
    public void testIncrementLikes() {
        comment.incrementLikes();
        assertEquals(1, comment.getLikes());
    }

    @Test
    public void testDecrementLikesForValidLikes() {
        comment.setLikes(1);
        comment.decrementLikes();
        assertEquals(0, comment.getLikes());
    }

    @Test
    public void testRefuseWhenTheLikesAreZero() {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                comment.setLikes(0);
                comment.decrementLikes();
            }
        });

        assertEquals(0, comment.getLikes());
    }

    @Test
    public void testIncrementShares() {
        comment.incrementShares();
        assertEquals(1, comment.getShares());
    }

    @Test
    public void testPrePersistOnCreate() {
        comment.onCreate();
        Date createdAt = comment.getCreatedAt();
        assertNotNull(createdAt);
        assertTrue(createdAt.getTime() <= System.currentTimeMillis());
    }

    @Test
    public void testPreupdate() {
        comment.onUpdate();
        Date updatedDate = comment.getUpdateAt();
        assertNotNull(updatedDate);
        assertTrue(updatedDate.getTime() <= System.currentTimeMillis());
    }
}
