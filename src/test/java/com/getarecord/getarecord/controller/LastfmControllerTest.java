package com.getarecord.getarecord.controller;

import com.getarecord.getarecord.config.TestDataProperties;
import com.getarecord.getarecord.dto.Album;
import com.getarecord.getarecord.dto.Artist;
import com.getarecord.getarecord.service.Lastfm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LastfmControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    LastfmController lastfmController;

    @MockitoBean
    Lastfm lastfm;

    @Autowired
    TestDataProperties testData;

    @Value("${artist.mbid.defaultValue}")
    private String artistMbidDefaultValue;

    @Value("${album.mbid.defaultValue}")
    private String albumMbidDefaultValue;

    @Test
    void contextLoads() {
        assertThat(lastfmController).isNotNull();
    }

    @Test
    void shouldCallServiceWithDefaultArtistMbidWhenNoParamProvided() {
        Artist mockArtist = new Artist(
                testData.getArtist().getName(),
                artistMbidDefaultValue,
                new String[]{"dubstep"},
                "bio"
        );
        when(lastfm.getArtistInfosFromMbid(artistMbidDefaultValue))
                .thenReturn(mockArtist);

        ResponseEntity<Artist> response = restTemplate.getForEntity("/api/artist", Artist.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(testData.getArtist().getName());
        verify(lastfm).getArtistInfosFromMbid(artistMbidDefaultValue);
    }

    @Test
    void shouldCallServiceWithProvidedArtistMbid() {
        Artist mockArtist = new Artist(
                testData.getArtist().getName(),
                testData.getArtist().getMbid(),
                new String[]{"rock"},
                "bio"
        );
        when(lastfm.getArtistInfosFromMbid(testData.getArtist().getMbid()))
                .thenReturn(mockArtist);

        ResponseEntity<Artist> response = restTemplate.getForEntity(
                "/api/artist?mbid=" + testData.getArtist().getMbid(),
                Artist.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getMbid()).isEqualTo(testData.getArtist().getMbid());
        verify(lastfm).getArtistInfosFromMbid(testData.getArtist().getMbid());
    }

    @Test
    void shouldCallServiceWithDefaultAlbumMbidWhenNoParamProvided() {
        Album mockAlbum = new Album(
                testData.getAlbum().getName(),
                testData.getAlbum().getArtist(),
                albumMbidDefaultValue,
                "image.jpg",
                new String[]{"dubstep"},
                null,
                "100",
                "2007",
                "summary"
        );
        when(lastfm.getAlbumInfosFromMbid(albumMbidDefaultValue))
                .thenReturn(mockAlbum);

        ResponseEntity<Album> response = restTemplate.getForEntity("/api/album", Album.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(testData.getAlbum().getName());
        verify(lastfm).getAlbumInfosFromMbid(albumMbidDefaultValue);
    }

    @Test
    void shouldCallServiceWithProvidedAlbumMbid() {
        Album mockAlbum = new Album(
                testData.getAlbum().getName(),
                testData.getAlbum().getArtist(),
                testData.getAlbum().getMbid(),
                "image.jpg",
                new String[]{"rock"},
                null,
                "200",
                "2020",
                "summary"
        );
        when(lastfm.getAlbumInfosFromMbid(testData.getAlbum().getMbid()))
                .thenReturn(mockAlbum);

        ResponseEntity<Album> response = restTemplate.getForEntity(
                "/api/album?mbid=" + testData.getAlbum().getMbid(),
                Album.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getMbid()).isEqualTo(testData.getAlbum().getMbid());
        verify(lastfm).getAlbumInfosFromMbid(testData.getAlbum().getMbid());
    }
}