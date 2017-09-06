package me.jokey.movie.model.api;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wz on 2017/8/25 15:41.
 * desc:
 */
public class RxSchedulers {

    public static <T> ObservableTransformer<T, T> io() {
        return upstream -> upstream.subscribeOn(Schedulers.io());
    }

    public static <T> ObservableTransformer<T, T> main() {
        return upstream -> upstream.observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ObservableTransformer<T, T> io_main() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
