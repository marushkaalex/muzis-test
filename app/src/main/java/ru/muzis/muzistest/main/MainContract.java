package ru.muzis.muzistest.main;

import ru.muzis.muzistest.base.BasePresenter;
import ru.muzis.muzistest.model.ArtistListModel;

public interface MainContract {
    interface View {
        void showArtistList(ArtistListModel model);

        void showError(Throwable error);
    }

    interface Presenter extends BasePresenter{
        void loadArtists();
    }
}
