package com.getarecord.getarecord.lasftm;

import com.fasterxml.jackson.databind.JsonNode;
import com.getarecord.getarecord.record.Artist;
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

    public Lastfm(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    // wrapper around lastfm API "tag.getTopAlbums"
    // https://www.last.fm/api/show/tag.getTopAlbums
//    public Record getRandomRecordFromTag(String tag) {
//        // TODO: generate random number for param "page"
//        // TODO: get random record's mbid
//        // TODO: get record infos from mbid with getRecordInfosFromMbid()
//        // TODO: get Artist with infos with getArtistWithInfo()
//        // TODO: map infos to Artist & Record objects
//        // TODO: return Record object
//    }

    // wrapper around lastfm API "album.getinfo"
    // https://www.last.fm/api/show/album.getInfo
//    public JsonNode getRecordInfosFromMbid(String mbid) {
//        // TODO: fetch infos from lastfm
//        // TODO: build Record object
//    }

    // wrapper around lastfm API "artist.getInfo"
    // https://www.last.fm/api/show/artist.getInfo
    public Artist getArtistWithInfo(String artist) {
        String url = String.format(
                "%s/?method=artist.getinfo&artist=%s&api_key=%s&format=json",
                baseUrl, artist, apiKey
        );

        JsonNode response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        JsonNode artistNode = response.get("artist");

        String name = artistNode.get("name").asText();
        String mbid = artistNode.get("mbid").asText();

        JsonNode tagsNode = artistNode.get("tags").get("tag");
        String[] tags = new String[tagsNode.size()];
        for (int i = 0; i < tagsNode.size(); i++) {
            tags[i] = tagsNode.get(i).get("name").asText();
        }

        String bio = artistNode.get("bio").get("summary").asText();

        return new Artist(name, mbid, tags, bio);
    }
}
