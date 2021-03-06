package me.jokey.movie.contract;

import java.util.List;

import me.jokey.movie.model.MovieEntity;

/**
 * Created by wz on 2017/9/6 19:01.
 * desc:
 */
public class MovieContract {

    public interface View {
        void loadMovieTop(List<MovieEntity> list);
    }

    public interface Presenter {
        void getMovieTop(int category, int count, int start);
    }

}
