package com.musicShare.musicShare.services.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.musicShare.entities.PlayList;
import com.musicShare.entities.UserDetails;
import com.musicShare.repositories.PlayListRepository;
import com.musicShare.repositories.UserDetailsRepository;
import com.musicShare.services.repositories.PlayListService;

@RunWith(MockitoJUnitRunner.class)
public class PlayListServiceTests {

    @Mock
    private PlayListRepository playListRepository;

    @Mock
    private UserDetailsRepository userDetailsRepository;

    @InjectMocks
    private PlayListService playListService;

    @Before
    public void onSetUp() {
        UserDetails user = new UserDetails();
        user.setId(1L);

        Authentication auth = new UsernamePasswordAuthenticationToken(user, null);

        SecurityContextHolder.getContext().setAuthentication(auth);

    }

    @Test
    public void savePlayListTest() {
        UserDetails user = new UserDetails();
        user.setId(1L);
        when(userDetailsRepository.findById(user.getId())).thenReturn(Optional.of(user));
        PlayList playList = new PlayList();
        playList.setName("Test Playlist");
        playList.setUser(user);
        when(playListRepository.save(playList)).thenReturn(playList);
        PlayList savedPlaylist = playListService.savePlayList(playList);
        assertNotNull(savedPlaylist);
        assertEquals(savedPlaylist.getName(), playList.getName());
        assertEquals(savedPlaylist.getUser(), playList.getUser());
    }

    @Test(expected = RuntimeException.class)
    public void savePlayListInvalidUserTest() {
        UserDetails user = new UserDetails();
        user.setId(1L);
        when(userDetailsRepository.findById(user.getId())).thenReturn(Optional.empty());
        PlayList playList = new PlayList();
        playList.setName("Test Playlist");
        playList.setUser(user);
        playListService.savePlayList(playList);
    }

    @Test
    public void getAllPlaylistsForCurrentUserTest() {
        UserDetails user = new UserDetails();
        user.setId(1L);
        when(userDetailsRepository.findById(user.getId())).thenReturn(Optional.of(user));
        PlayList playList = new PlayList();
        playList.setName("Test Playlist");
        playList.setUser(user);
        List<PlayList> playLists = new ArrayList<>();
        playLists.add(playList);
        when(playListRepository.findByUser(user)).thenReturn(playLists);
        List<PlayList> playlistsForCurrentUser = playListService.getAllPlaylistsForCurrentUser();
        assertNotNull(playlistsForCurrentUser);
        assertEquals(playlistsForCurrentUser.size(), playLists.size());
        assertEquals(playlistsForCurrentUser.get(0).getName(), playList.getName());
        assertEquals(playlistsForCurrentUser.get(0).getUser(), playList.getUser());
    }

    @Test
    public void updatePlayListTest() {
        UserDetails user = new UserDetails();
        user.setId(1L);
        when(userDetailsRepository.findById(user.getId())).thenReturn(Optional.of(user));
        PlayList playList = new PlayList();
        playList.setName("Test Playlist");
        playList.setUser(user);
        when(playListRepository.findById(playList.getId())).thenReturn(Optional.of(playList));
        PlayList updatedPlaylist = new PlayList();
        updatedPlaylist.setId(1L);
        updatedPlaylist.setName("Updated Playlist");
        updatedPlaylist.setUser(user);
        when(playListRepository.save(updatedPlaylist)).thenReturn(updatedPlaylist);
        PlayList result = playListService.updatePlayList(playList.getId(), updatedPlaylist);
        assertNotNull(result);
        assertEquals(result.getName(), updatedPlaylist.getName());
        assertEquals(result.getUser(), updatedPlaylist.getUser());
    }

    @Test
    public void deletePlayListTest() {
        UserDetails user = new UserDetails();
        user.setId(1L);
        when(userDetailsRepository.findById(user.getId())).thenReturn(Optional.of(user));
        PlayList playList = new PlayList();
        playList.setId(1L);
        playList.setName("Test Playlist");
        playList.setUser(user);
        when(playListRepository.findById(playList.getId())).thenReturn(Optional.of(playList));
        boolean result = playListService.deletePlayList(playList.getId());
        assertTrue(result);
    }
}