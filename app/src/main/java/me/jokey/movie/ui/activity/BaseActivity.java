package me.jokey.movie.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wz on 2017/9/6 16:59.
 * desc:
 */
public abstract class BaseActivity extends RxAppCompatActivity {

    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnBinder = ButterKnife.bind(this);
        initData(savedInstanceState);
    }

    protected abstract int getLayoutId();

    protected abstract void initData(@Nullable Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) mUnBinder.unbind();
    }

}
