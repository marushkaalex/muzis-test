package ru.muzis.muzistest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ArtistListModel extends BaseModel {
    @JsonProperty("artist_list")
    private List<ArtistModel> artistList;

    public List<ArtistModel> getArtistList() {
        return artistList;
    }

    public void setArtistList(List<ArtistModel> artistList) {
        this.artistList = artistList;
    }
}
