
package com.musicShare.musicShare.repositories;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.musicShare.musicShare.entities.Song;

@DataJpaTest

public class SongRepositoryTests {
    @Autowired
    private SongRepository repository;

    @Test
    public void testFindByArtist() {

        Song song1 = new Song("song1", "artist1", "song123");
        Song song2 = new Song("song2", "artist2", "song124");
        Song song3 = new Song("song3", "artist1", "song125");

        repository.save(song1);
        repository.save(song2);
        repository.save(song3);

        List<Song> songs = repository.findByArtist("artist1");

        assertEquals(2, songs.size());
        assertTrue(songs.contains(song1));
        assertTrue(songs.contains(song3));
    }

    @Test
    public void testFindBySongId() {
        // create a few Song objects with different songIds
        Song song1 = new Song("song1", "artist1", "song123");
        Song song2 = new Song("song2", "artist2", "song124");
        Song song3 = new Song("song3", "artist1", "song125");

        // save them to the database
        repository.save(song1);
        repository.save(song2);
        repository.save(song3);

        // retrieve the songs by calling findBySongId method
        List<Song> songs = repository.findBySongId("song2");

        // verify that the returned list contains only the songs with the specified
        // songId
        assertEquals(1, songs.size());
        assertTrue(songs.contains(song2));
    }

    @Test
    public void testFindByArtistWithNonExistingArtist() {
        // call findByArtist method with an artist that doesn't exist in the database
        List<Song> songs = repository.findByArtist("non-existing-artist");

        // verify that the returned list is empty
        assertTrue(songs.isEmpty());
    }

    @Test
    public void testFindBySongIdWithNonExistingSongId() {
        // call findBySongId method with a songId that doesn't exist in the database
        List<Song> songs = repository.findBySongId("non-existing-song-id");

        // verify that the returned list is empty
        assertTrue(songs.isEmpty());
    }
}
