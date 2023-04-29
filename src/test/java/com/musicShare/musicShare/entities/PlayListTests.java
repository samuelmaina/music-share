package com.musicShare.musicShare.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayListTests {

    private PlayList playlist;

    @BeforeEach
    void setUp() {
        playlist = new PlayList("My Playlist");
    }

    @Test
    void testGetAndSetName() {
        assertEquals("My Playlist", playlist.getName());
        playlist.setName("New Playlist");
        assertEquals("New Playlist", playlist.getName());
    }

    @Test
    void testGetAndSetIsPublic() {
        assertFalse(playlist.isPublic());
        playlist.setPublic(true);
        assertTrue(playlist.isPublic());
    }

    @Test
    void testGetAndSetCreatedAt() {
        assertNull(playlist.getCreatedAt());
        Date createdAt = new Date(System.currentTimeMillis());
        playlist.setCreatedAt(createdAt);
        assertEquals(createdAt, playlist.getCreatedAt());
    }

    @Test
    void testGetAndSetUpdatedAt() {
        assertNull(playlist.getUpdatedAt());
        Date updatedAt = new Date(System.currentTimeMillis());
        playlist.setUpdatedAt(updatedAt);
        assertEquals(updatedAt, playlist.getUpdatedAt());
    }

    @Test
    void testGetAndSetDescription() {
        assertEquals("", playlist.getDescription());
        playlist.setDescription("New description");
        assertEquals("New description", playlist.getDescription());
    }

    @Test
    void testGetAndSetUser() {
        assertNull(playlist.getUser());
        UserDetails user = new UserDetails();
        user.setId(1L);
        playlist.setUser(user);
        assertEquals(user, playlist.getUser());
    }

    @Test
    void testGetAndSetSongs() {
        assertTrue(playlist.getSongs().isEmpty());
        Song song1 = new Song();

        Song song2 = new Song();

        playlist.getSongs().add(song1);
        playlist.getSongs().add(song2);
        assertEquals(2, playlist.getSongs().size());
        assertTrue(playlist.getSongs().contains(song1));
        assertTrue(playlist.getSongs().contains(song2));
        playlist.getSongs().remove(song1);
        assertEquals(1, playlist.getSongs().size());
        assertFalse(playlist.getSongs().contains(song1));
        assertTrue(playlist.getSongs().contains(song2));
    }

    @Test
    void testOnCreate() {
        assertNull(playlist.getCreatedAt());
        assertNull(playlist.getUpdatedAt());
        playlist.onCreate();
        assertNotNull(playlist.getCreatedAt());
        assertNotNull(playlist.getUpdatedAt());
        assertEquals(playlist.getCreatedAt(), playlist.getUpdatedAt());
    }

    @Test
    void testOnUpdate() {
        assertNull(playlist.getUpdatedAt());
        playlist.onUpdate();
        assertNotNull(playlist.getUpdatedAt());
    }
}
