package ru.muzis.muzistest.main;

import java.util.List;

import ru.muzis.muzistest.base.BasePresenter;
import ru.muzis.muzistest.model.ArtistModel;

public interface MainContract {
    interface View {
        void showArtistList(List<ArtistModel> model);

        void showError(Throwable error);

        void showProgress();

        void hideProgress();
    }

    interface Presenter extends BasePresenter {
        void loadArtists();
    }
}
