package ru.muzis.muzistest.base;

import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<PRESENTER extends BasePresenter> extends AppCompatActivity {
    protected PRESENTER mPresenter;

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }
}
