package com.musicShare.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musicShare.entities.PlayList;
import com.musicShare.entities.UserDetails;
import com.musicShare.repositories.PlayListRepository;
import com.musicShare.repositories.UserDetailsRepository;
import com.musicShare.services.controllers.JWTAuthenticationFilter;
import com.musicShare.services.controllers.YouTubeService;
import com.musicShare.services.repositories.PlayListService;

@RestController
@RequestMapping(value = "/youtube")
public class YoutubeController {

@Autowired
private YouTubeService youtubeService;

@GetMapping("/{song_name}")
public ResponseEntity<SearchResult> getPlayListById(@PathVariable String
song_name) {
List<SearchResult> songs = youtubeService.searchSongs(song_name);
return ResponseEntity.ok(songs);
}
}
