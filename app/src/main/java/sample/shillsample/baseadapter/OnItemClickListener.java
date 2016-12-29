package sample.shillsample.baseadapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by we on 2016/12/4.
 */

public interface OnItemClickListener<T> {
    void onItemClick(ViewGroup parent, View view, T t, int position);
    boolean onItemLongClick(ViewGroup parent, View view, T t, int position);
}
