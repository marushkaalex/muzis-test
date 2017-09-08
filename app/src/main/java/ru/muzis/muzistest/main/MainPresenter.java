package ru.muzis.muzistest.main;

import io.reactivex.disposables.Disposable;
import ru.muzis.muzistest.App;
import ru.muzis.muzistest.api.ApiInteractor;
import ru.muzis.muzistest.base.BasePresenterAbs;

public class MainPresenter extends BasePresenterAbs implements MainContract.Presenter {
    private MainContract.View mView;
    private ApiInteractor mApiInteractor;

    public MainPresenter(MainContract.View view) {
        mView = view;
        mApiInteractor = App.getInstance().getApiInteractor();
    }

    @Override
    public void loadArtists() {
        loadArtists(false);
    }

    private void loadArtists(boolean isRefreshing) {
        if (!isRefreshing) {
            mView.showProgress();
        }
        Disposable topArtiststWithTopTracks = mApiInteractor.getTopArtiststWithTopTracks(
                artists -> {
                    hideProgress(isRefreshing);
                    mView.showArtistList(artists);
                },
                error -> {
                    hideProgress(isRefreshing);
                    mView.showError(error);
                }
        );
        mDisposables.add(topArtiststWithTopTracks);
    }

    private void hideProgress(boolean isRefreshing) {
        if (isRefreshing) {
            mView.setRefreshing(false);
        } else {
            mView.hideProgress();
        }
    }

    @Override
    public void refresh() {
        mDisposables.clear();
        loadArtists(true);
    }
}
