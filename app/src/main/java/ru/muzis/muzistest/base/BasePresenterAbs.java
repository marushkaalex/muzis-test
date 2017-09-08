package ru.muzis.muzistest.base;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenterAbs implements BasePresenter {
    protected CompositeDisposable mDisposables;

    public BasePresenterAbs() {
        mDisposables = new CompositeDisposable();
    }

    @Override
    public void onDestroy() {
        mDisposables.clear();
    }
}
