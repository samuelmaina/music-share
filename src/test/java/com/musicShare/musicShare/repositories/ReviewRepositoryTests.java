// package com.musicShare.musicShare.repositories;

// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.mockito.Mock;
// import org.mockito.junit.MockitoJUnitRunner;

// import com.musicShare.musicShare.entities.Song;

// @RunWith(MockitoJUnitRunner.class)
// public class ReviewRepositoryTests {

// @Mock
// private ReviewRepository reviewRepository;

// @Test
// public void testFindByPlayList() {
// // Create a mock Song object
// Song song = new Song();
// song.setName("My Song");
// song.setPlayListId(1L);

// // Define the expected result of the repository method
// List<Song> expectedSongs = Arrays.asList(song);

// // Set up the mock repository to return the expected result
// when(reviewRepository.findByPlayList(1L)).thenReturn(expectedSongs);

// // Call the repository method
// List<Song> actualSongs = reviewRepository.findByPlayList(1L);

// // Verify that the repository method was called with the correct arguments
// verify(reviewRepository).findByPlayList(1L);

// // Verify that the actual result matches the expected result
// assertEquals(expectedSongs, actualSongs);
// }

// @Test
// public void testFindByUserId() {
// // Create a mock Song object
// Song song = new Song();
// song.setName("My Song");
// song.setUserId(1L);

// // Define the expected result of the repository method
// List<Song> expectedSongs = Arrays.asList(song);

// // Set up the mock repository to return the expected result
// when(reviewRepository.findByUserId(1L)).thenReturn(expectedSongs);

// // Call the repository method
// List<Song> actualSongs = reviewRepository.findByUserId(1L);

// // Verify that the repository method was called with the correct arguments
// verify(reviewRepository).findByUserId(1L);

// // Verify that the actual result matches the expected result
// assertEquals(expectedSongs, actualSongs);
// }
// }
