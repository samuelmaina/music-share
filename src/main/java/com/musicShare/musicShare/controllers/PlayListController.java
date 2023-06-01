package com.musicShare.musicShare.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musicShare.musicShare.entities.PlayList;
import com.musicShare.musicShare.repositories.PlayListRepository;

import com.musicShare.musicShare.services.repositories.PlayListService;

@RestController
@RequestMapping(value = "/playlist")
public class PlayListController {

    @Autowired
    private PlayListRepository playListRepository;

    @Autowired
    private PlayListService playListService;

    @GetMapping
    public List<PlayList> getAllPlaylists() {
        return playListRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayList> getPlayListById(@PathVariable Long id) {
        Optional<PlayList> PlayListOptional = playListRepository.findById(id);
        if (PlayListOptional.isPresent()) {
            return ResponseEntity.ok(PlayListOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public PlayList createPlayList(@RequestBody PlayList playList) {
        return playListService.savePlayList(playList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayList> updatePlayList(@PathVariable Long id, @RequestBody PlayList playList) {
        PlayList updated = playListService.updatePlayList(id, playList);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayList(@PathVariable Long id) {
        boolean isDeleted = playListService.deletePlayList(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
