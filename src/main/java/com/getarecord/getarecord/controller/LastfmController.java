package com.getarecord.getarecord.controller;

import com.getarecord.getarecord.dto.Album;
import com.getarecord.getarecord.dto.Artist;
import com.getarecord.getarecord.service.Lastfm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LastfmController {
    private final Lastfm lastfm;

    public LastfmController(Lastfm lastfm) {
        this.lastfm = lastfm;
    }

    @Value("${artist.mbid.defaultValue}")
    private String artistMbidDefaultValue;

    @Value("${album.mbid.defaultValue}")
    private String albumMbidDefaultValue;

    @GetMapping("/artist")
    public Artist artist(@RequestParam Optional<String> mbid) {
        return lastfm.getArtistInfosFromMbid(mbid.orElse(artistMbidDefaultValue));
    }

    @GetMapping("/album")
    public Album album(@RequestParam() Optional<String> mbid) {
        return lastfm.getAlbumInfosFromMbid(mbid.orElse(albumMbidDefaultValue));
    }
}