package sample.shillsample.ui.databinding.module;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by we on 2016/12/19.
 */

public class ObservableUser extends BaseObservable {
    @Bindable
    public String mFirstName;
    @Bindable
    public String mLastName;

    public void setmFirstName(String firstName) {
        this.mFirstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    public void setmLastName(String lastName) {
        this.mLastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }
}
