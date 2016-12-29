package sample.shillsample.ui.databinding.module;

/**
 * Created by we on 2016/12/19.
 */

public class Contact {
    private final  String mTel;
    private final String mAddress;

    public Contact(String tel, String address) {
        this.mTel = tel;
        this.mAddress = address;
    }

    public String getTel() {
        return mTel;
    }

    public String getAddress() {
        return mAddress;
    }
}
