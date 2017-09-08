package ru.muzis.muzistest.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.muzis.muzistest.R;
import ru.muzis.muzistest.base.BaseActivity;
import ru.muzis.muzistest.model.ArtistModel;

public class MainActivity
        extends BaseActivity<MainContract.Presenter>
        implements MainContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.main_recycler)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.main_progressBar)
    protected ProgressBar mProgressBar;
    @BindView(R.id.main_swipeToRefresh)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mPresenter = new MainPresenter(this);
        mPresenter.loadArtists();
    }

    @Override
    public void showArtistList(List<ArtistModel> models) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new ChartAdapter(this, models));
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }

    @Override
    public void setRefreshing(boolean isRefreshing) {
        mSwipeRefreshLayout.setRefreshing(isRefreshing);
    }

    @Override
    public void showError(Throwable error) {
        new AlertDialog.Builder(this).setMessage(error.getMessage()).show();
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }
}
