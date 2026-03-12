package com.getarecord.getarecord.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getarecord.getarecord.dto.Album;
import com.getarecord.getarecord.dto.Artist;
import com.getarecord.getarecord.service.Lastfm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LastfmController {
    private final Lastfm lastfm;
    private final ObjectMapper objectMapper;

    public LastfmController(Lastfm lastfm, ObjectMapper objectMapper) {
        this.lastfm = lastfm;
        this.objectMapper = objectMapper;
    }

    @Value("${dev.mode:false}")
    private boolean devMode;

    @Value("${artist.mbid.defaultValue}")
    private String artistMbidDefaultValue;

    @Value("${album.mbid.defaultValue}")
    private String albumMbidDefaultValue;

    @GetMapping("/artist")
    public Artist artist(@RequestParam Optional<String> mbid) throws IOException {
        if (devMode) {
            ClassPathResource resource = new ClassPathResource("mock/artist.json");
            return objectMapper.readValue(resource.getInputStream(), Artist.class);
        }
        return lastfm.getArtistInfosFromMbid(mbid.orElse(artistMbidDefaultValue));
    }

    @GetMapping("/album")
    public Album album(@RequestParam() Optional<String> mbid) {
        return lastfm.getAlbumInfosFromMbid(mbid.orElse(albumMbidDefaultValue));
    }
}