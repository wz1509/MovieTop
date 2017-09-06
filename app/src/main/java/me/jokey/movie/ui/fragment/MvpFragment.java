package me.jokey.movie.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import me.jokey.movie.presenter.BasePresenter;
import me.jokey.movie.ui.interfaceView.IView;

/**
 * Created by wz on 2017/9/6 19:09.
 * desc:
 */
public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment implements IView {

    protected P mPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        if (mPresenter != null) mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    public void onRequestFailed(int code, String msg) {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) mPresenter.detachView();
        super.onDestroyView();
    }
}
