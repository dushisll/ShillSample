package sample.shillsample.ui.mine.fragment;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;

import butterknife.BindView;
import sample.shillsample.R;
import sample.shillsample.baseui.BaseFragment;

/**
 * Created by we on 2016/12/7.
 */

public class MyFragment extends BaseFragment {
    @BindView(R.id.content_fragment)
    FrameLayout contentFragment;
    @BindView(R.id.bottom_view)
    BottomNavigationView bottomView;

    private FragmentManager mFragmentManager;

    private MainFragment mainFragment;
    private MainSelfFragment mainSelfFragment;
    private MainGirlFragment mainGirlFragment;
    private MainFashionFragment mainFashionFragment;

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initData() {
        setNewRootFragment( R.id.index);
    }


    private void setNewRootFragment(int id) {
        mFragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideFragment(transaction);
        Fragment fragment = null;
        switch (id){
            case R.id.index:
                mainFragment = (MainFragment) mFragmentManager.findFragmentByTag("index");
                if(null == mainFragment){
                    mainFragment = new MainFragment();
                    transaction.add(R.id.content_fragment,mainFragment,"index");
                }else{
                    transaction.show(mainFragment);
                }
                break;

            case R.id.myself:
                mainSelfFragment = (MainSelfFragment) mFragmentManager.findFragmentByTag("myself");
                if(null == mainSelfFragment){
                    mainSelfFragment = new MainSelfFragment();
                    transaction.add(R.id.content_fragment,mainSelfFragment,"myself");
                }else{
                    transaction.show(mainSelfFragment);
                }
                break;

            case R.id.xiaoyun:
                mainGirlFragment = (MainGirlFragment) mFragmentManager.findFragmentByTag("girl");
                if(null == mainGirlFragment){
                    mainGirlFragment = new MainGirlFragment();
                    transaction.add(R.id.content_fragment,mainGirlFragment,"girl");
                }else{
                    transaction.show(mainGirlFragment);
                }
                break;

            case R.id.girl:
                mainFashionFragment = (MainFashionFragment) mFragmentManager.findFragmentByTag("fashion");
                if(null == mainFashionFragment){
                    mainFashionFragment = new MainFashionFragment();
                    transaction.add(R.id.content_fragment,mainFashionFragment,"fashion");
                }else{
                    transaction.show(mainFashionFragment);
                }
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (null != mainFragment) {
            transaction.hide(mainFragment);
        }
        if (null != mainSelfFragment) {
            transaction.hide(mainSelfFragment);
        }
        if (null != mainGirlFragment) {
            transaction.hide(mainGirlFragment);
        }
        if (null != mainFashionFragment) {
            transaction.hide(mainFashionFragment);
        }
    }

    @Override
    protected void initListener() {
        bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                setNewRootFragment(item.getItemId());
                return true;
            }
        });
    }

    @Override
    protected void lazyLoadData() {
    }

}
