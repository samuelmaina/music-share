package com.musicShare.musicShare.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import com.musicShare.CodeApplication;
import com.musicShare.entities.PlayList;
import com.musicShare.entities.UserDetails;
import com.musicShare.repositories.PlayListRepository;

@DataJpaTest

public class PlayListRepositoryListTests {

    @Autowired
    private PlayListRepository playListRepository;

    @Test
    public void testFindByName() {
        // create test data
        UserDetails user = new UserDetails();
        user.setEmail("testuser@test.com");
        user.setPassword("testpassword");

        PlayList playlist1 = new PlayList();
        playlist1.setName("Test Playlist 1");
        playlist1.setUser(user);
        playListRepository.save(playlist1);

        PlayList playlist2 = new PlayList();
        playlist2.setName("Test Playlist 2");
        playlist2.setUser(user);
        playListRepository.save(playlist2);

        // test findByName
        List<PlayList> foundPlaylists = playListRepository.findByName("Test Playlist 1");
        assertThat(foundPlaylists.size()).isEqualTo(1);
        assertThat(foundPlaylists.get(0)).isEqualTo(playlist1);
    }

    @Test
    public void testFindByUser() {
        // create test data
        UserDetails user1 = new UserDetails();
        user1.setEmail("testuser1@test.com");
        user1.setPassword("testpassword1");

        UserDetails user2 = new UserDetails();
        user2.setEmail("testuser2@test.com");
        user2.setPassword("testpassword2");

        PlayList playlist1 = new PlayList();
        playlist1.setName("Test Playlist 1");
        playlist1.setUser(user1);
        playListRepository.save(playlist1);

        PlayList playlist2 = new PlayList();
        playlist2.setName("Test Playlist 2");
        playlist2.setUser(user2);
        playListRepository.save(playlist2);

        // test findByUser
        List<PlayList> foundPlaylists = playListRepository.findByUser(user1);
        assertThat(foundPlaylists.size()).isEqualTo(1);
        assertThat(foundPlaylists.get(0)).isEqualTo(playlist1);
    }
}
