package com.getarecord.getarecord.dto;

public class Artist {
    private final String name;
    private final String mbid;
    private final String[] tags;
    private final String bio;

    public Artist(String name, String mbid, String[] tags, String bio) {
        this.name = name;
        this.mbid = mbid;
        this.tags = tags;
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public String getMbid() {
        return mbid;
    }

    public String[] getTags() {
        return tags;
    }

    public String getBio() {
        return bio;
    }
}
