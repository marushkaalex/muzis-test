package ru.muzis.muzistest.api;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ru.muzis.muzistest.Consts;
import ru.muzis.muzistest.model.ArtistModel;
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

        return single(mApiManager.getTopArtists(Consts.COUNTRY_CODE)
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

        return single(mApiManager.getTopTracks(Consts.COUNTRY_CODE)
                .map(i ->
                        Stream.of(i.getMessage().getBody().getTrackList())
                                .map(TrackModel.Wrapper::getTrack)
                                .collect(Collectors.toList())))
                .subscribe(onSuccess, onError);
    }
}
