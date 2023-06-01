package com.musicShare.musicShare.controllers;

import com.musicShare.musicShare.services.controllers.*;
import com.musicShare.musicShare.services.repositories.PlayListService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static com.jayway.jsonpath.JsonPath.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicShare.musicShare.entities.PlayList;
import com.musicShare.musicShare.entities.UserDetails;
import com.musicShare.musicShare.repositories.PlayListRepository;
import com.musicShare.musicShare.repositories.UserDetailsRepository;

public class PlayListControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayListService playListService;

    @MockBean
    private PlayListRepository playListRepository;

    @Test
    public void testGetAllPlaylists() throws Exception {
        PlayList playList1 = new PlayList();
        playList1.setId(1L);
        playList1.setName("Playlist 1");

        PlayList playList2 = new PlayList();
        playList2.setId(2L);
        playList2.setName("Playlist 2");

        List<PlayList> playLists = new ArrayList<>();
        playLists.add(playList1);
        playLists.add(playList2);

        Mockito.when(playListRepository.findAll()).thenReturn(playLists);

        mockMvc.perform(get("/playlist"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Playlist 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Playlist 2"));
    }

    @Test
    public void testGetPlayListById() throws Exception {
        PlayList playList = new PlayList();
        playList.setId(1L);
        playList.setName("Playlist 1");

        Mockito.when(playListRepository.findById(1L)).thenReturn(Optional.of(playList));

        mockMvc.perform(get("/playlist/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Playlist 1"));
    }

    @Test
    public void testGetPlayListByIdNotFound() throws Exception {
        Mockito.when(playListRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/playlist/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreatePlayList() throws Exception {
        PlayList playList = new PlayList();
        playList.setName("Playlist 1");

        Mockito.when(playListService.savePlayList(playList)).thenReturn(playList);

        String requestBody = "{\"name\": \"Playlist 1\"}";

        mockMvc.perform(post("/playlist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").doesNotExist())
                .andExpect(jsonPath("$.name").value("Playlist 1"));
    }

    @Test
    public void testUpdatePlayList() throws Exception {
        PlayList existingPlayList = new PlayList();
        existingPlayList.setId(1L);
        existingPlayList.setName("Playlist 1");

        PlayList updatedPlayList = new PlayList();
        updatedPlayList.setId(1L);
        updatedPlayList.setName("Updated Playlist");

        Mockito.when(playListService.updatePlayList(1L,
                updatedPlayList)).thenReturn(updatedPlayList);
        Mockito.when(playListRepository.findById(1L)).thenReturn(Optional.of(existingPlayList));

        String requestBody = "{\"name\": \"Updated Playlist\"}";

        mockMvc.perform(put("/playlist/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Playlist"));
    }

}
