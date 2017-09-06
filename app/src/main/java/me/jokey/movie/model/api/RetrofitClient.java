package me.jokey.movie.model.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wz on 2017/8/24 19:52.
 * desc:
 */
public class RetrofitClient {

    /**
     * 请求超时时间
     */
    private static final int DEFAULT_TIMEOUT = 10 * 1000;

    /**
     * 服务器地址url
     */
    private static final String BASE_URL = ApiService.Base_URL;
    private static ApiService sApiService;

    public static ApiService getDefault() {
        if (null == sApiService) {
            synchronized (RetrofitClient.class) {
                if (null == sApiService) {
                    //手动创建一个OkHttpClient并设置超时时间
                    OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
                    //设置超时时间
                    httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
                    httpClientBuilder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
                    httpClientBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
                    sApiService = new Retrofit.Builder()
                            .client(httpClientBuilder.build())
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .baseUrl(BASE_URL)
                            .build().create(ApiService.class);
                }
            }
        }
        return sApiService;
    }

}
