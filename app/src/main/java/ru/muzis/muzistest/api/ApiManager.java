package ru.muzis.muzistest.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.muzis.muzistest.model.ArtistModel;
import ru.muzis.muzistest.model.BaseResponseModel;
import ru.muzis.muzistest.model.TrackModel;

public interface ApiManager {
    @GET("chart.artists.get")
    Single<BaseResponseModel<ArtistModel.ListWrapper>> getTopArtists(@Query("country") String countryCode);

    @GET("chart.tracks.get")
    Single<BaseResponseModel<TrackModel.ListWrapper>> getTopTracks(@Query("country") String countryCode);
}
