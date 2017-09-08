package ru.muzis.muzistest.main;

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
        mView.showProgress();
        disposable(mApiInteractor.getTopArtiststWithTopTracks(
                artists -> {
                    mView.hideProgress();
                    mView.showArtistList(artists);
                },
                error -> {
                    mView.hideProgress();
                    mView.showError(error);
                }
        ));
    }
}
