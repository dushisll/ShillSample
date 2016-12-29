package sample.shill.mylibrary.baseui;

import android.content.Context;

/**
 * Created by we on 2016/12/3.
 */

public abstract class BasePresenter<T> implements IPresenter<T> {
    public Context mActivity;
    public T mView;

    @Override
    public void attachView(T view, Context context) {
        this.mView = view;
        this.mActivity = context;
        this.onStart();
    }

    @Override
    public void detachView() {
        this.mView = null;

    }

    public void onStart(){

    }
}
