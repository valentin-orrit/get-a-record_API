package com.getarecord.getarecord.record;

import java.util.HashMap;

public class Record {
    private final String name;
    private final Artist artist;
    private final String mbid;
    private final String image;
    private final String[] tag;
    private final HashMap<Integer, String> tracklist;
    private final String listeners;
    private final String publicationDate;
    private final String summary;

    public Record(String name, Artist artist, String mbid, String image, String[] tag, HashMap<Integer, String> tracklist, String listeners, String publicationDate, String summary) {
        this.name = name;
        this.artist = artist;
        this.mbid = mbid;
        this.image = image;
        this.tag = tag;
        this.tracklist = tracklist;
        this.listeners = listeners;
        this.publicationDate = publicationDate;
        this.summary = summary;
    }

    public String getName() {
        return name;
    }

    public Artist getArtist() {
        return artist;
    }

    public String getMbid() {
        return mbid;
    }

    public String getImage() {
        return image;
    }

    public String[] getTag() {
        return tag;
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
