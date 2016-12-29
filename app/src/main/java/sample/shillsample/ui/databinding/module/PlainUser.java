package sample.shillsample.ui.databinding.module;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

/**
 * Created by we on 2016/12/19.
 */

public class PlainUser {
    public ObservableField<String> firstName = new ObservableField<>();
    public ObservableField<String> lastName = new ObservableField<>();
    public ObservableInt age = new ObservableInt();
}
