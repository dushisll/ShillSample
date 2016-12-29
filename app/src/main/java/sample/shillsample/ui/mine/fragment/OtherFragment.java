package sample.shillsample.ui.mine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sample.shillsample.R;
import sample.shillsample.baseui.BaseFragment;
import sample.shillsample.ui.databinding.DataBindingActivity;
import sample.shillsample.ui.main.MainActivity;

/**
 * Created by we on 2016/12/11.
 */

public class OtherFragment extends BaseFragment {
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_other;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void lazyLoadData() {

    }


    @OnClick({R.id.btn1, R.id.btn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(getActivity(), sample.shillsample.ui.databinding.MainActivity.class));
                break;
        }
    }
}
