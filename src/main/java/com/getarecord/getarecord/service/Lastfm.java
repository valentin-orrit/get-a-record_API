package com.getarecord.getarecord.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.getarecord.getarecord.dto.Album;
import com.getarecord.getarecord.dto.Artist;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class Lastfm {

    @Value("${lastfm.api.key}")
    private String apiKey;

    @Value("${lastfm.api.baseUrl}")
    private String baseUrl;

    private final WebClient webClient;
    private final Mapper mapper;

    public Lastfm(WebClient.Builder webClientBuilder, Mapper mapper) {
        this.webClient = webClientBuilder.build();
        this.mapper = mapper;
    }

    // wrapper around lastfm API "tag.getTopAlbums"
    // https://www.last.fm/api/show/tag.getTopAlbums
//    public Record getRandomAlbumFromTag(String tag) {
//        // TODO: generate random number for param "page"
//        // get random record's mbid
//        // get record infos from mbid with getRecordInfosFromMbid()
//        // get Artist with infos with getArtistWithInfo()
//        // map infos to Artist & Record objects
//        // return Record object
//    }

    // wrapper around lastfm API "album.getinfo"
    // https://www.last.fm/api/show/album.getInfo
    public Album getAlbumInfosFromMbid(String mbid) {
        JsonNode response = fetchAndBuildJson("album", mbid);
        return mapper.mapToAlbum(response, mbid);
    }

    // wrapper around lastfm API "artist.getInfo"
    // https://www.last.fm/api/show/artist.getInfo
    public Artist getArtistInfosFromMbid(String mbid) {
        JsonNode response = fetchAndBuildJson("artist", mbid);
        return mapper.mapToArtist(response);
    }

    private JsonNode fetchAndBuildJson(String method, String mbid) {
        String url = String.format(
                "%s/?method=%s.getinfo&mbid=%s&api_key=%s&format=json",
                baseUrl, method, mbid, apiKey
        );

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();
    }
}
