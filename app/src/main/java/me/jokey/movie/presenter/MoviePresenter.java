package me.jokey.movie.presenter;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import me.jokey.movie.contract.MovieContract;
import me.jokey.movie.model.MovieEntity;
import me.jokey.movie.model.api.RetrofitClient;
import me.jokey.movie.model.api.RxSchedulers;
import me.jokey.movie.ui.fragment.MovieFragment;
import okhttp3.ResponseBody;

/**
 * Created by wz on 2017/9/6 19:11.
 * desc:
 */
public class MoviePresenter extends BasePresenter<MovieFragment> implements MovieContract.Presenter {

    @Override
    public void getMovieTop(int category, int count, int start) {
        Observable<ResponseBody> observable = null;
        if (category == 0)
            observable = RetrofitClient.getDefault().getMovieOnNow(count, start);
        else if (category == 1)
            observable = RetrofitClient.getDefault().getMovieOnNext(count, start);
        else if (category == 2)
            observable = RetrofitClient.getDefault().getMovieTop(count, start);

        if (observable == null) return;
        observable.compose(RxSchedulers.io_main())
                .map(responseBody -> get(new JSONObject(responseBody.string())))
                .subscribe(movieEntities -> mView.loadMovieTop(movieEntities),
                        throwable -> mView.onRequestFailed(0, throwable.getMessage()),
                        () -> mView.onRequestEnd());
    }

    private List<MovieEntity> get(JSONObject jsonObject) throws JSONException {
        List<MovieEntity> list = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("subjects");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonItem = jsonArray.getJSONObject(i);
            MovieEntity entity = new Gson().fromJson(jsonItem.toString(), MovieEntity.class);
            list.add(entity);
        }
        return list;
    }

}
