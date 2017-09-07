package ru.muzis.muzistest.api;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ru.muzis.muzistest.model.ArtistModel;
import ru.muzis.muzistest.model.BaseModel;
import ru.muzis.muzistest.model.BaseResponseModel;

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

        return single(mApiManager.getTopArtists()
                .map(i ->
                        Stream.of(i.getMessage().getBody().getArtistList())
                                .map(ArtistModel.Wrapper::getArtist)
                                .collect(Collectors.toList())))
                .subscribe(onSuccess, onError);
    }

    private static class ResponseBodyConsumer<I extends BaseModel, T extends BaseResponseModel<I>>
            implements Consumer<T> {

        private Consumer<I> consumerToWrap;

        public ResponseBodyConsumer(Consumer<I> consumerToWrap) {
            this.consumerToWrap = consumerToWrap;
        }

        @Override
        public void accept(T t) throws Exception {
            consumerToWrap.accept(t.getMessage().getBody());
        }
    }
}
