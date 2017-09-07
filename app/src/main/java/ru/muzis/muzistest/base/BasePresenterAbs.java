package ru.muzis.muzistest.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenterAbs implements BasePresenter {
    protected CompositeDisposable mDisposables;

    public BasePresenterAbs() {
        mDisposables = new CompositeDisposable();
    }

    @Override
    public void onDestroy() {
        mDisposables.dispose();
    }

    protected void disposable(Disposable disposable) {
        mDisposables.add(disposable);
    }
}
