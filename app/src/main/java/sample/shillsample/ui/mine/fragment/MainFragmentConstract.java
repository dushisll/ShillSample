package sample.shillsample.ui.mine.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import java.util.List;

import sample.shillsample.baseadapter.BaseFragmentPageAdapter;
import sample.shillsample.baseui.IPresenter;
import sample.shillsample.bean.Channel;

/**
 * Created by we on 2016/12/8.
 */

public interface MainFragmentConstract {
    interface View{
        void loadMainChannel(List<Channel> channels);
    }

    interface Presenter extends IPresenter<View>{
        void loadMainChannel();

    }

}
