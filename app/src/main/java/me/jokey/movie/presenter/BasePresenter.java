package me.jokey.movie.presenter;

import java.lang.ref.WeakReference;

import me.jokey.movie.ui.interfaceView.IView;

public abstract class BasePresenter<V extends IView> implements IPresenter {

    private WeakReference<IView> actReference;
    protected V mView;

    @Override
    public void attachView(IView iView) {
        actReference = new WeakReference<>(iView);
        mView = (V) actReference.get();
    }

    @Override
    public void detachView() {
        if (actReference != null) {
            actReference.clear();
            actReference = null;
        }
    }

}
