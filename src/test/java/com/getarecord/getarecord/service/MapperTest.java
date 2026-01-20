package com.getarecord.getarecord.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getarecord.getarecord.config.TestDataProperties;
import com.getarecord.getarecord.dto.Album;
import com.getarecord.getarecord.dto.Artist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MapperTest {

    private Mapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestDataProperties testData;

    @BeforeEach
    void setUp() {
        mapper = new Mapper();
    }

    @Test
    void shouldMapJsonToArtist() throws IOException {
        JsonNode mockJson = objectMapper.readTree(
                new ClassPathResource("lastfm/mockArtist.json").getInputStream()
        );

        Artist artist = mapper.mapToArtist(mockJson);

        assertThat(artist).isNotNull();
        assertThat(artist.getName()).isEqualTo(testData.getArtist().getName());
        assertThat(artist.getMbid()).isEqualTo(testData.getArtist().getMbid());
        assertThat(artist.getTags()).contains("dubstep", "ambient", "electronic");
        assertThat(artist.getBio()).contains("William Emmanuel Bevan");
    }

    @Test
    void shouldMapJsonToAlbum() throws IOException {
        JsonNode mockJson = objectMapper.readTree(
                new ClassPathResource("lastfm/mockAlbum.json").getInputStream()
        );

        Album album = mapper.mapToAlbum(mockJson, testData.getAlbum().getMbid());

        assertThat(album).isNotNull();
        assertThat(album.getName()).isEqualTo(testData.getAlbum().getName());
        assertThat(album.getArtist()).isEqualTo(testData.getAlbum().getArtist());
        assertThat(album.getMbid()).isEqualTo(testData.getAlbum().getMbid());
        assertThat(album.getImage()).isNotEmpty();
        assertThat(album.getTags()).contains("dubstep", "ambient", "electronic");
        assertThat(album.getListeners()).isEqualTo("931327");
        assertThat(album.getPublicationDate()).isEqualTo("10 Oct 2020, 06:38");
        assertThat(album.getSummary()).contains("Burial");
    }

    @Test
    void shouldMapTracklist() throws IOException {
        JsonNode mockJson = objectMapper.readTree(
                new ClassPathResource("lastfm/mockAlbum.json").getInputStream()
        );

        Album album = mapper.mapToAlbum(mockJson, testData.getAlbum().getMbid());

        assertThat(album.getTracklist()).isNotNull();
        assertThat(album.getTracklist()).containsEntry(1, "Untitled");
        assertThat(album.getTracklist()).containsEntry(2, "Archangel");
        assertThat(album.getTracklist()).hasSize(13);
    }
}