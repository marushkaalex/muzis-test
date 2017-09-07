package ru.muzis.muzistest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ArtistListModel extends BaseModel {
    @JsonProperty("artist_list")
    private List<ArtistModel.Wrapper> artistList;

    public List<ArtistModel.Wrapper> getArtistList() {
        return artistList;
    }

    public void setArtistList(List<ArtistModel.Wrapper> artistList) {
        this.artistList = artistList;
    }
}
