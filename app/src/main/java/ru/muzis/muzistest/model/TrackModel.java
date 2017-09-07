package ru.muzis.muzistest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TrackModel extends BaseModel {
    @JsonProperty("track_id")
    private long trackId;
    @JsonProperty("track_name")
    private String name;
    @JsonProperty("artist_id")
    private long artistId;

    public long getTrackId() {
        return trackId;
    }

    public void setTrackId(long trackId) {
        this.trackId = trackId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    public static class Wrapper extends BaseModel {
        @JsonProperty("track")
        private TrackModel track;

        public TrackModel getTrack() {
            return track;
        }

        public void setTrack(TrackModel track) {
            this.track = track;
        }
    }

    public static class ListWrapper extends BaseModel {
        @JsonProperty("track_list")
        private List<Wrapper> trackList;

        public List<Wrapper> getTrackList() {
            return trackList;
        }

        public void setTrackList(List<Wrapper> trackList) {
            this.trackList = trackList;
        }
    }
}
