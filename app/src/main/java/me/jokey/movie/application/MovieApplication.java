package me.jokey.movie.application;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by wz on 2017/9/7 15:07.
 * desc:
 */
public class MovieApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

    }

}
