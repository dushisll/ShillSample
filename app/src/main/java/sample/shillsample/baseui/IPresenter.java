package sample.shillsample.baseui;

import android.content.Context;

/**
 * Created by we on 2016/12/3.
 */

public interface IPresenter<T> {
    void attachView(T view, Context context);

    void detachView();
}
