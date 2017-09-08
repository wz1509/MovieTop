package me.jokey.movie.contract;

import me.jokey.movie.model.MovieDetailEntity;

/**
 * Created by wz on 2017/9/7 19:06.
 * desc:
 */
public class MovieDetailContract {

    public interface View {
        void loadMovieDetail(MovieDetailEntity movie);
    }

    public interface Presenter {
        void getMovieDetail(String movieId);
    }

}
