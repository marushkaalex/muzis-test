package ru.muzis.muzistest.api;

import android.util.LongSparseArray;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ru.muzis.muzistest.Consts;
import ru.muzis.muzistest.model.ArtistModel;
import ru.muzis.muzistest.model.BaseResponseModel;
import ru.muzis.muzistest.model.TrackModel;

public class ApiInteractor {
    private ApiManager mApiManager;

    public ApiInteractor(ApiManager apiManager) {
        mApiManager = apiManager;
    }

    private <T> Single<T> single(Single<T> single) {
        return single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Disposable getTopArtists(
            Consumer<List<ArtistModel>> onSuccess,
            Consumer<Throwable> onError
    ) {

        return single(mApiManager.getTopArtists(Consts.COUNTRY_CODE, Consts.TOP_COUNT)
                .map(i ->
                        Stream.of(i.getMessage().getBody().getArtistList())
                                .map(ArtistModel.Wrapper::getArtist)
                                .collect(Collectors.toList())))
                .subscribe(onSuccess, onError);
    }

    public Disposable getTopTracks(
            Consumer<List<TrackModel>> onSuccess,
            Consumer<Throwable> onError
    ) {

        return single(mApiManager.getTopTracks(Consts.COUNTRY_CODE, Consts.TOP_COUNT)
                .map(i ->
                        Stream.of(i.getMessage().getBody().getTrackList())
                                .map(TrackModel.Wrapper::getTrack)
                                .collect(Collectors.toList())))
                .subscribe(onSuccess, onError);
    }

    public Disposable getTopArtiststWithTopTracks(
            Consumer<List<ArtistModel>> onSuccess,
            Consumer<Throwable> onError
    ) {
        return single(mApiManager.getTopTracks(Consts.COUNTRY_CODE, Consts.TOP_COUNT)
                .map(i -> {
                    List<TrackModel.Wrapper> trackList = i.getMessage().getBody().getTrackList();
                    LongSparseArray<List<TrackModel>> artistTrackMap = new LongSparseArray<>();
                    for (TrackModel.Wrapper wrapper : trackList) {
                        TrackModel track = wrapper.getTrack();
                        long artistId = track.getArtistId();
                        List<TrackModel> trackModels = artistTrackMap.get(artistId);

                        if (trackModels == null) {
                            trackModels = new ArrayList<>();
                            artistTrackMap.append(artistId, trackModels);
                        }

                        trackModels.add(track);
                    }
                    return artistTrackMap;
                })
                .map(
                        map -> {
                            BaseResponseModel<ArtistModel.ListWrapper> model =
                                    mApiManager.getTopArtists(Consts.COUNTRY_CODE, Consts.TOP_COUNT)
                                            .toFuture().get();

                            return Stream.of(model.getMessage().getBody().getArtistList())
                                    .map(i -> {
                                        ArtistModel artist = i.getArtist();
                                        artist.setTracks(map.get(artist.getId()));
                                        return artist;
                                    })
                                    .collect(Collectors.toList());
                        }
                ))
                .subscribe(
                        onSuccess,
                        onError
                );
    }
}
