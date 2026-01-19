package com.getarecord.getarecord.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.getarecord.getarecord.dto.Artist;
import com.getarecord.getarecord.dto.Album;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Mapper {
    public Artist mapToArtist(JsonNode response) {
        JsonNode artistNode = response.get("artist");

        String name = artistNode.get("name").asText();
        String mbid = artistNode.get("mbid").asText();

        String[] tags = mapTags(artistNode);

        String bio = artistNode.get("bio").get("summary").asText();

        return new Artist(name, mbid, tags, bio);
    }

    public Album mapToAlbum(JsonNode response, String mbid) {
        JsonNode albumNode = response.get("album");

        String name = albumNode.get("name").asText();
        String artist = albumNode.get("artist").asText();
        String[] tags = mapTags(albumNode);
        String image = extractLastImage(albumNode);
        HashMap<Integer, String> tracklist = mapTracklist(albumNode);
        String listeners = albumNode.get("listeners").asText();
        String publicationDate = albumNode.get("wiki").get("published").asText();
        String summary = albumNode.get("wiki").get("summary").asText();

        return new Album(name, artist, mbid, image, tags, tracklist, listeners, publicationDate, summary);
    }

    // helper method to parse tags from JSON API response
    private String[] mapTags(JsonNode node) {
        JsonNode tagsNode = node.get("tags").get("tag");
        String[] tags = new String[tagsNode.size()];
        for (int i = 0; i < tagsNode.size(); i++) {
            tags[i] = tagsNode.get(i).get("name").asText();
        }

        return tags;
    }

    // helper method to extract last (supposedly original) image from the JSON API response
    private String extractLastImage(JsonNode node) {
        if (!node.has("image")) return "";

        JsonNode images = node.get("image");

        return images.get(images.size() - 1).get("#text").asText();
    }

    // helper method to map the tracklist from JSON API response to a HashMap
    private HashMap<Integer, String> mapTracklist(JsonNode node) {
        var tracklist = new HashMap<Integer, String>();

        if (!node.has("tracks")) return tracklist;

        JsonNode responseTracks = node.get("tracks").get("track");

        for (JsonNode track : responseTracks) {
            int rank = track.get("@attr").get("rank").asInt();
            String trackName = track.get("name").asText();
            tracklist.put(rank, trackName);
        }

        return tracklist;
    }
}
