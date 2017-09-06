package me.jokey.movie.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import me.jokey.movie.R;
import me.jokey.movie.contract.TopContract;
import me.jokey.movie.model.MovieEntity;
import me.jokey.movie.presenter.TopPresenter;
import me.jokey.movie.ui.TopAdapter;
import me.jokey.movie.util.LogUtils;

/**
 * Created by wz on 2017/9/6 17:25.
 * desc:
 */
public class TopFragment extends MvpFragment<TopPresenter> implements TopContract.View {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private static final int COUNT = 15;
    private int mPage = 1;

    private TopAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_top;
    }

    @Override
    protected void initData(View view, @Nullable Bundle savedInstanceState) {
        mAdapter = new TopAdapter(getContext());

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        mPresenter.getMovieTop(COUNT, mPage);
    }

    @Override
    public void loadMovieTop(List<MovieEntity> list) {
        LogUtils.debugLongInfo(list.toString());
        mAdapter.setNewData(list);
        mAdapter.loadMoreComplete();
    }

    @Override
    public void onRequestFailed(int code, String msg) {
        super.onRequestFailed(code, msg);
        LogUtils.warnInfo(msg);
    }

    @Override
    protected TopPresenter createPresenter() {
        return new TopPresenter();
    }


}
