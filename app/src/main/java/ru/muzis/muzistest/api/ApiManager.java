package ru.muzis.muzistest.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import ru.muzis.muzistest.model.ArtistListModel;

public interface ApiManager {
    @GET("chart.artists.get")
    Single<ArtistListModel> getTopArtists();
}
