package sample.shillsample.ui.mine.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import sample.shillsample.Constant;
import sample.shillsample.R;
import sample.shillsample.baseadapter.BaseFragmentPageAdapter;
import sample.shillsample.baseui.BaseFragment;
import sample.shillsample.bean.Channel;
import sample.shillsample.utils.TabUtils;

/**
 * Created by we on 2016/12/7.
 */

public class MainFragment extends BaseFragment<MainFragmentPresenter> implements MainFragmentConstract.View{
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.id_add)
    ImageView mAdd;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private BaseFragmentPageAdapter fragmentAdapter;

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initData() {
        mPresenter.loadMainChannel();
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void lazyLoadData() {
    }


    @OnClick(R.id.id_add)
    public void onClick() {
        Toast.makeText(getActivity(),"添加频道",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadMainChannel(List<Channel> channels) {

        if(channels != null){
            List<String> channelNames = new ArrayList<>();
            List<Fragment> fragmentList = new ArrayList<>();
            for(int i = 0;i<channels.size();i++){
                channelNames.add(channels.get(i).getChannelName());
                fragmentList.add(new MainListFragment());
            }
            if(fragmentAdapter == null){
                fragmentAdapter = new BaseFragmentPageAdapter(getChildFragmentManager()
                        ,fragmentList,channelNames);
            }else{
                fragmentAdapter.setFragments(getChildFragmentManager(),fragmentList,channelNames);
            }
            mViewPager.setAdapter(fragmentAdapter);
            tabs.setupWithViewPager(mViewPager);
            TabUtils.dynamicSetTabLayoutMode(tabs);
            setPageChangeListener();
        }
    }

    private MainListFragment createListFragments(Channel channel) {
        MainListFragment fragment = new MainListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.CHANNEL_NAME, channel.getChannelName());
        fragment.setArguments(bundle);
        return fragment;
    }

    private void setPageChangeListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }




}
