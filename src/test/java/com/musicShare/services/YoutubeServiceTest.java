import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTube.Search;
import com.google.api.services.youtube.YouTube.Search.List;
import com.google.api.services.youtube.YouTube.Search.List.Builder;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.musicShare.services.YouTubeService;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class YouTubeServiceTest {
    private YouTubeService youTubeService;
    private YouTube youTube;
    private Search search;
    private List list;

    @BeforeEach
    void setUp() throws GeneralSecurityException, IOException {
        ReflectionTestUtils.setField(youTubeService, "youtube.APP_NAME", "secret");
        youTube = mock(YouTube.class);
        search = mock(Search.class);
        list = mock(List.class);
        when(youTube.search()).thenReturn(search);
        when(search.list(List.of("id", "snippet"))).thenReturn(list);
        youTubeService = new YouTubeService(youTube);
    }

    @Test
    void testSearchSongs() throws IOException {
        String phrase = "some phrase";
        String videoId = "123";
        String title = "some title";
        String channelTitle = "some channel title";
        SearchResult searchResult = new SearchResult()
                .setId(new SearchResult.Id().setVideoId(videoId))
                .setSnippet(new SearchResult.Snippet().setTitle(title).setChannelTitle(channelTitle));
        List<SearchResult> searchResults = new ArrayList<>();
        searchResults.add(searchResult);
        SearchListResponse response = new SearchListResponse().setItems(searchResults);
        when(list.setMaxResults(YouTubeService.MAX_RESULTS)).thenReturn(list);
        when(list.setType("video")).thenReturn(list);
        when(list.setFields("items(id(videoId),snippet(title,channelTitle))")).thenReturn(list);
        when(list.setQ(phrase)).thenReturn(list);
        when(list.execute()).thenReturn(response);
        List<SearchResult> actualSearchResults = youTubeService.searchSongs(phrase);
        assertEquals(searchResults, actualSearchResults);
    }
}
