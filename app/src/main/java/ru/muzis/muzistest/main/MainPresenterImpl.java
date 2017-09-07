package ru.muzis.muzistest.main;

import ru.muzis.muzistest.App;
import ru.muzis.muzistest.api.ApiInteractor;
import ru.muzis.muzistest.base.BasePresenterAbs;

public class MainPresenterImpl extends BasePresenterAbs implements MainContract.Presenter {
    private MainContract.View mView;
    private ApiInteractor mApiInteractor;

    public MainPresenterImpl(MainContract.View view) {
        mView = view;
        mApiInteractor = App.getInstance().getApiInteractor();
    }

    @Override
    public void loadArtists() {
        disposable(mApiInteractor.getTopArtists(
                artists -> mView.showArtistList(artists),
                error -> mView.showError(error)
        ));
    }
}
