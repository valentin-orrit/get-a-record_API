package com.getarecord.getarecord.dto;

import java.util.HashMap;

public class Album {
    private final String name;
    private final String artist;
    private final String mbid;
    private final String image;
    private final String[] tags;
    private final HashMap<Integer, String> tracklist;
    private final String listeners;
    private final String publicationDate;
    private final String summary;

    public Album(String name, String artist, String mbid, String image, String[] tags, HashMap<Integer, String> tracklist, String listeners, String publicationDate, String summary) {
        this.name = name;
        this.artist = artist;
        this.mbid = mbid;
        this.image = image;
        this.tags = tags;
        this.tracklist = tracklist;
        this.listeners = listeners;
        this.publicationDate = publicationDate;
        this.summary = summary;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getMbid() {
        return mbid;
    }

    public String getImage() {
        return image;
    }

    public String[] getTags() {
        return tags;
    }

    public HashMap<Integer, String> getTracklist() {
        return tracklist;
    }

    public String getListeners() {
        return listeners;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getSummary() {
        return summary;
    }
}
