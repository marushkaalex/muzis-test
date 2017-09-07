package ru.muzis.muzistest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ArtistModel extends BaseModel {
    @JsonProperty("artist_id")
    private long id;
    @JsonProperty("artist_mbid")
    private String mbid;
    @JsonProperty("artist_name")
    private String name;

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
}
