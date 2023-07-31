package com.musicShare.musicShare.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.musicShare.entities.RentPlayList;

public class RentPlayListTests {

    private RentPlayList playlist;

    @BeforeEach
    public void setUp() {
        playlist = new RentPlayList(123456L, 7890L);
    }

    @Test
    public void testGetRenderer() {
        assertEquals(123456L, playlist.getRenderer());
    }

    @Test
    public void testSetRenderer() {
        playlist.setRenderer(987654L);
        assertEquals(987654L, playlist.getRenderer());
    }

    @Test
    public void testGetRenderee() {
        assertEquals(7890L, playlist.getRenderee());
    }

    @Test
    public void testSetRenderee() {
        playlist.setRenderee(5678L);
        assertEquals(5678L, playlist.getRenderee());
    }

    @Test
    public void testGetStartDate() {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        assertTrue(playlist.getStartDate().before(now) || playlist.getStartDate().equals(now));
    }

    @Test
    public void testSetStartDate() {
        Timestamp date = Timestamp.valueOf(LocalDateTime.of(2022, 9, 15, 12, 30, 0));
        playlist.setStartDate(date);
        assertEquals(date, playlist.getStartDate());
    }

}
