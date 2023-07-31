package com.musicShare.services;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

public class YouTubeService {
    @Value("${youtube.APP_NAME}")
    private static final String APPLICATION_NAME;
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final long MAX_RESULTS = 10L; // maximum number of search results to retrieve

    private final YouTube youTube;

    public YouTubeService() throws GeneralSecurityException, IOException {
        this.youTube = new YouTube.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                null)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public List<SearchResult> searchSongs(String phrase) throws IOException {
        YouTube.Search.List searchList = youTube.search()
                .list(List.of("id", "snippet")) // include id and snippet data in the results
                .setMaxResults(MAX_RESULTS)
                .setType("video") // only search for videos
                .setFields("items(id(videoId),snippet(title,channelTitle))")
                .setQ(phrase); // search phrase

        SearchListResponse response = searchList.execute();
        return response.getItems();
    }
}
