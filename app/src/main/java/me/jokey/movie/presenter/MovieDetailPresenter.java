package me.jokey.movie.presenter;

import me.jokey.movie.contract.MovieDetailContract;
import me.jokey.movie.model.api.RetrofitClient;
import me.jokey.movie.model.api.RxSchedulers;
import me.jokey.movie.ui.activity.MovieDetailActivity;

/**
 * Created by wz on 2017/9/7 19:07.
 * desc:
 */
public class MovieDetailPresenter extends BasePresenter<MovieDetailActivity> implements
        MovieDetailContract.Presenter {

    @Override
    public void getMovieDetail(String movieId) {
        RetrofitClient.getDefault()
                .getMovieDetail(movieId)
                .compose(RxSchedulers.io_main())
                .subscribe(movieDetailEntity -> mView.loadMovieDetail(movieDetailEntity),
                        throwable -> mView.onRequestFailed(0, throwable.getMessage()));
    }

}
