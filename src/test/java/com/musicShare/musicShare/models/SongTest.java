package com.musicShare.musicShare.models;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SongTest {
    @Test
    public void testGetName() {
        Song song = new Song();
        song.setName("Hotel California");
        assertEquals("Hotel California", song.getName());
    }

    @Test
    public void testGetArtist() {
        Song song = new Song();
        song.setArtist("Eagles");
        assertEquals("Eagles", song.getArtist());
    }

    @Test
    public void testGetViews() {
        Song song = new Song();
        song.setViews(1000000L);
        assertEquals(1000000L, song.getViews().longValue());
    }

    @Test
    public void testGetSongId() {
        Song song = new Song();
        song.setSongId("2345JPK");
        assertEquals("2345JPK", song.getSongId());
    }

    @Test
    public void testSetName() {
        Song song = new Song();
        song.setName("Stairway to Heaven");
        assertEquals("Stairway to Heaven", song.getName());
    }

    @Test
    public void testSetArtist() {
        Song song = new Song();
        song.setArtist("Led Zeppelin");
        assertEquals("Led Zeppelin", song.getArtist());
    }

    @Test
    public void testSetViews() {
        Song song = new Song();
        song.setViews(5000000L);
        assertEquals(5000000L, song.getViews().longValue());
    }

    @Test
    public void testSetSongId() {
        Song song = new Song();
        song.setSongId("53748834LBJ");
        assertEquals("53748834LBJ", song.getSongId());
    }
}
