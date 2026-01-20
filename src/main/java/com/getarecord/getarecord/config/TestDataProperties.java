package com.getarecord.getarecord.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "test")
public class TestDataProperties {

    private Artist artist = new Artist();
    private Album album = new Album();

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public static class Artist {
        private String name;
        private String mbid;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMbid() {
            return mbid;
        }

        public void setMbid(String mbid) {
            this.mbid = mbid;
        }
    }

    public static class Album {
        private String name;
        private String artist;
        private String mbid;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public String getMbid() {
            return mbid;
        }

        public void setMbid(String mbid) {
            this.mbid = mbid;
        }
    }
}