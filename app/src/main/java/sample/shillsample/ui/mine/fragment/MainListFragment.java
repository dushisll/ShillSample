package sample.shillsample.ui.mine.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sample.shillsample.Constant;
import sample.shillsample.R;
import sample.shillsample.baseui.BaseFragment;

/**
 * Created by we on 2016/12/9.
 */

public class MainListFragment extends BaseFragment {

    @BindView(R.id.id_tv)
    TextView mTv;

    private String title;
    @Override
    protected int getlayoutId() {
        return R.layout.fragment_fashion;
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
        if (getArguments() != null) {
            title = getArguments().getString(Constant.CHANNEL_NAME);
            mTv.setText(title);
        }
    }

}
