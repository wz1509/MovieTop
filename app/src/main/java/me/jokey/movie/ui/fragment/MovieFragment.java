package me.jokey.movie.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import me.jokey.movie.R;
import me.jokey.movie.application.Constant;
import me.jokey.movie.contract.TopContract;
import me.jokey.movie.model.MovieEntity;
import me.jokey.movie.presenter.TopPresenter;
import me.jokey.movie.ui.adapter.MovieAdapter;
import me.jokey.movie.util.LogUtils;

/**
 * Created by wz on 2017/9/6 17:25.
 * desc:
 */
public class MovieFragment extends MvpFragment<TopPresenter> implements TopContract.View,
        SwipeRefreshLayout.OnRefreshListener {

    private boolean isFirstVisible = true;
    private boolean isFirstInvisible = true;
    private boolean isPrepared;

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private static final int COUNT = 30;
    private int mStart;
    private int mCategory;

    private boolean isRefresh = false;
    private MovieAdapter mAdapter;
    private View mErrorView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        initPrepare();
    }

    private synchronized void initPrepare() {
        if (isPrepared) {
            onFirstUserVisible();
        } else {
            isPrepared = true;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isFirstVisible) {
            isFirstVisible = false;
            initPrepare();
        } else if (isFirstInvisible) {
            isFirstInvisible = false;
        }
    }

    private void onFirstUserVisible() {
        mCategory = getArguments().getInt(Constant.ACTION_CATEGORY, -1);
        if (mCategory < 0) {
            Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
            return;
        }
        mAdapter = new MovieAdapter(getContext());
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshListener(this);
        mAdapter.setOnLoadMoreListener(() ->
                mPresenter.getMovieTop(mCategory, COUNT, mStart), mRecyclerView);
        mErrorView = LayoutInflater.from(getContext())
                .inflate(R.layout.status_failed, (ViewGroup) mRecyclerView.getParent(), false);
        mErrorView.setOnClickListener(view -> {
            mAdapter.setEmptyView(R.layout.status_loading);
            onRefresh();
        });
        mAdapter.setEmptyView(R.layout.status_loading);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        // api是从0开始的
        mStart = 0;
        isRefresh = true;
        mPresenter.getMovieTop(mCategory, COUNT, mStart);
    }

    @Override
    public void loadMovieTop(List<MovieEntity> list) {
        if (list != null && list.size() > 0) {
            if (isRefresh) {
                mAdapter.setNewData(list);
                mStart = 0;
                isRefresh = false;
            } else {
                mAdapter.addData(list);
            }
            mStart += COUNT;
            mAdapter.loadMoreComplete();
        } else {
            if (isRefresh) {
                ((TextView) mErrorView.findViewById(R.id.status_text))
                        .setText(R.string.recycler_status_empty);
                mAdapter.setEmptyView(mErrorView);
            } else {
                mAdapter.loadMoreEnd();
            }
        }
    }

    @Override
    public void onRequestFailed(int code, String msg) {
        super.onRequestFailed(code, msg);
        LogUtils.warnInfo(msg);
        if (mRefreshLayout.isRefreshing()) mRefreshLayout.setRefreshing(false);
        if (isRefresh) {
            mAdapter.setNewData(null);
            ((TextView) mErrorView.findViewById(R.id.status_text)).setText(msg);
            mAdapter.setEmptyView(mErrorView);
        } else {
            mAdapter.loadMoreFail();
        }
    }

    @Override
    public void onRequestEnd() {
        super.onRequestEnd();
        if (mRefreshLayout.isRefreshing()) mRefreshLayout.setRefreshing(false);
    }

    @Override
    protected TopPresenter createPresenter() {
        return new TopPresenter();
    }


}
