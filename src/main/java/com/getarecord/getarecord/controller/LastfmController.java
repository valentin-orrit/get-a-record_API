package com.getarecord.getarecord.controller;

import com.getarecord.getarecord.dto.Album;
import com.getarecord.getarecord.dto.Artist;
import com.getarecord.getarecord.service.Lastfm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LastfmController {
    private final Lastfm lastfm;

    public LastfmController(Lastfm lastfm) {
        this.lastfm = lastfm;
    }

    @GetMapping("/artist")
    public Artist artist(@RequestParam(defaultValue = "9ddce51c-2b75-4b3e-ac8c-1db09e7c89c6") String mbid) {
        return lastfm.getArtistInfosFromMbid(mbid);
    }

    @GetMapping("/album")
    public Album album(@RequestParam(defaultValue = "02aa03a5-001b-4e5a-b3ad-23ad0fadb49c") String mbid) {
        return lastfm.getAlbumInfosFromMbid(mbid);
    }
}