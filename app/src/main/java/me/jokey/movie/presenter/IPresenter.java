package me.jokey.movie.presenter;

import me.jokey.movie.ui.interfaceView.IView;

/**
 * Created by wz on 2017/9/6 19:05.
 * desc:
 */
public interface IPresenter<V extends IView> {

    /**
     * @param view 绑定
     */
    void attachView(V view);


    /**
     * 防止内存的泄漏,清楚presenter与activity之间的绑定
     */
    void detachView();

}
