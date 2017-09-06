package me.jokey.movie.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import me.jokey.movie.presenter.BasePresenter;
import me.jokey.movie.ui.interfaceView.IView;

/**
 * Created by wz on 2017/9/6 19:16.
 * desc:
 */
public abstract class MvpActivity<P extends BasePresenter>  extends BaseActivity implements IView {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter != null) mPresenter.attachView(this);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.detachView();
    }

    @Override
    public void onRequestFailed(int code, String msg) {

    }

    @Override
    public void onRequestEnd() {

    }

}
