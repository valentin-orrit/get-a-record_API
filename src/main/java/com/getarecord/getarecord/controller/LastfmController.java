package com.getarecord.getarecord.controller;

import com.getarecord.getarecord.lasftm.Lastfm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LastfmController {
    private final Lastfm lastfm;

    public LastfmController(Lastfm lastfm) {
        this.lastfm = lastfm;
    }

    @GetMapping("/api/artist")
    public String artist(@RequestParam(defaultValue = "burial") String artist) {
        return lastfm.getArtistWithInfo(artist);
    }
}