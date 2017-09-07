package ru.muzis.muzistest.api;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ru.muzis.muzistest.model.ArtistListModel;

public class ApiInteractor {
    private ApiManager mApiManager;

    public ApiInteractor(ApiManager apiManager) {
        mApiManager = apiManager;
    }

    private  <T> Single<T> single(Single<T> single) {
        return single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Disposable getTopArtists(
            Consumer<? super ArtistListModel> onSuccess,
            Consumer<? super Throwable> onError
    ) {
        return single(mApiManager.getTopArtists()).subscribe(onSuccess, onError);
    }
}
