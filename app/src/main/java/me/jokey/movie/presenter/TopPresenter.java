package me.jokey.movie.presenter;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.jokey.movie.contract.TopContract;
import me.jokey.movie.model.MovieEntity;
import me.jokey.movie.model.api.RetrofitClient;
import me.jokey.movie.model.api.RxSchedulers;
import me.jokey.movie.ui.fragment.TopFragment;

/**
 * Created by wz on 2017/9/6 19:11.
 * desc:
 */
public class TopPresenter extends BasePresenter<TopFragment> implements TopContract.Presenter {

    @Override
    public void getMovieTop(int count, int start) {
        RetrofitClient.getDefault()
                .getMovieTop(count, start)
                .compose(RxSchedulers.io_main())
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
