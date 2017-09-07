package ru.muzis.muzistest.main;

import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.widget.TextView;

import butterknife.ButterKnife;
import ru.muzis.muzistest.R;
import ru.muzis.muzistest.base.BaseActivity;
import ru.muzis.muzistest.model.ArtistListModel;

public class MainActivity
        extends BaseActivity<MainContract.Presenter>
        implements MainContract.View {

    private TextView mTextView;
    private MainContract.Presenter mPresenter = new MainPresenterImpl(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = ButterKnife.findById(this, R.id.main_text);
        mPresenter.loadArtists();
    }

    @Override
    public void showArtistList(ArtistListModel model) {
        mTextView.setText(model.toString());
    }

    @Override
    public void showError(Throwable error) {
        mTextView.setText(error.getMessage());
    }
}
