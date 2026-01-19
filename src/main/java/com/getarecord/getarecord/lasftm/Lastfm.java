package com.getarecord.getarecord.lasftm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Lastfm {

    @Value("${lastfm.api.key}")
    private String apiKey;

    @Value("${lastfm.api.baseUrl}")
    private String baseUrl;

    // wrapper around lastfm API "artist.getInfo"
    // https://www.last.fm/api/show/artist.getInfo
    public String getArtistWithInfo(String artist) {
        String completeURL = String.format(
                "%s/?method=artist.getinfo&artist=%s&api_key=%s&format=json",
                baseUrl, artist, apiKey
        );

        System.out.println(completeURL);
        //JSONPObject data = "";

        return completeURL;
    }
}
