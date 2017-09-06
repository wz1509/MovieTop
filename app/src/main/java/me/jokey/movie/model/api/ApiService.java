package me.jokey.movie.model.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wz on 2017/8/18 13:55.
 * desc:
 */
public interface ApiService {

    String Base_URL = "http://api.douban.com/";

    @GET("/v2/movie/top250")
    Observable<ResponseBody> getMovieTop(@Query("count") int count, @Query("start") int start);


}
