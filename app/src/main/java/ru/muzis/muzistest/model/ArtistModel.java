package ru.muzis.muzistest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ArtistModel extends BaseModel {
    @JsonProperty("artist_id")
    private long id;
    @JsonProperty("artist_mbid")
    private String mbid;
    @JsonProperty("artist_name")
    private String name;
    private List<TrackModel> tracks;

    public List<TrackModel> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackModel> tracks) {
        this.tracks = tracks;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Wrapper extends BaseModel {
        @JsonProperty("artist")
        private ArtistModel artist;

        public ArtistModel getArtist() {
            return artist;
        }

        public void setArtist(ArtistModel artist) {
            this.artist = artist;
        }
    }

    public static class ListWrapper extends BaseModel {
        @JsonProperty("artist_list")
        private java.util.List<Wrapper> artistList;

        public java.util.List<Wrapper> getArtistList() {
            return artistList;
        }

        public void setArtistList(java.util.List<Wrapper> artistList) {
            this.artistList = artistList;
        }
    }
}
