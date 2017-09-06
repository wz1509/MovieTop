package me.jokey.movie.ui.interfaceView;

/**
 * Created by wz on 2017/9/6 19:03.
 * desc:
 */
public interface IView {

    void onRequestFailed(int code, String msg);

    void onRequestEnd();

}
