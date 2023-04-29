package com.musicShare.musicShare.services.repositories;

import com.musicShare.musicShare.entities.PlayList;
import com.musicShare.musicShare.entities.UserDetails;
import com.musicShare.musicShare.repositories.PlayListRepository;
import com.musicShare.musicShare.repositories.UserDetailsRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PlayListService {

    @Autowired
    private PlayListRepository playListRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public PlayList savePlayList(PlayList playList) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.parseLong(authentication.getName());

        UserDetails user = userDetailsRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        playList.setUser(user);

        return playListRepository.save(playList);
    }

    public List<PlayList> getAllPlaylistsForCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.parseLong(authentication.getName());

        UserDetails user = userDetailsRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return playListRepository.findByUser(user);

    }

    public PlayList updatePlayList(Long id, PlayList playList) {
        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());

        UserDetails user = userDetailsRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Optional<PlayList> PlayListOptional = playListRepository.findById(id);
        if (PlayListOptional.isPresent()) {
            playList.setUser(user);
            return playListRepository.save(playList);
        } else {
            return null;
        }
    }

    public boolean deletePlayList(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.parseLong(authentication.getName());
        userDetailsRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Optional<PlayList> PlayListOptional = playListRepository.findById(id);
        if (PlayListOptional.isPresent()) {
            playListRepository.delete(PlayListOptional.get());
            return true;
        } else {
            return false;
        }

    }

}
