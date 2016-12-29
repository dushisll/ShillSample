package sample.shillsample.ui.mine;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sample.shillsample.R;
import sample.shillsample.baseui.BaseActivity;
import sample.shillsample.ui.login.LoginActivity;
import sample.shillsample.ui.main.MainActivity;
import sample.shillsample.ui.mine.fragment.AboutAuthorFragment;
import sample.shillsample.ui.mine.fragment.MainFashionFragment;
import sample.shillsample.ui.mine.fragment.MainFragment;
import sample.shillsample.ui.mine.fragment.MainGirlFragment;
import sample.shillsample.ui.mine.fragment.MainSelfFragment;
import sample.shillsample.ui.mine.fragment.MyFragment;
import sample.shillsample.ui.mine.fragment.OtherFragment;
import sample.shillsample.ui.mine.fragment.SettingFragment;

/**
 * Created by we on 2016/12/5.
 */

public class MyActivity extends BaseActivity {
    @BindView(R.id.toolBar)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation)
    NavigationView mNavigation;
    @BindView(R.id.contentLayout)
    FrameLayout mContentLayout;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    private FragmentManager mFragmentManager;

    private MyFragment myFragment;
    private OtherFragment otherFragment;
    private AboutAuthorFragment aboutAuthorFragment;
    private SettingFragment settingFragment;

    @Override
    protected void initInjector() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_myframe;
    }

    @Override
    protected void initData() {
        mToolbar.setTitle("蠹石的Sample");
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                mToolbar,
                R.string.open,
                R.string.close) ;
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        initNavigationViewHeader();

        mDrawerLayout.closeDrawer(GravityCompat.START);
        setNewRootFragment(R.id.navigation_item_1);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void initNavigationViewHeader() {
        View view = mNavigation.inflateHeaderView(R.layout.drawer_header);
        ImageView img_header = (ImageView) view.findViewById(R.id.image_header);
        img_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyActivity.this, LoginActivity.class));
            }
        });
        TextView userName = (TextView) view.findViewById(R.id.userName);
        userName.setText(R.string.author);
        mNavigation.setNavigationItemSelectedListener(new NavigationItemSelected());
    }


    @OnClick(R.id.fab)
    public void onClick() {

    }


    class NavigationItemSelected implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            mDrawerLayout.closeDrawers();
            switch (menuItem.getItemId()) {
                case R.id.navigation_item_1:

                    menuItem.setChecked(true);
                    setNewRootFragment(R.id.navigation_item_1);

                    return true;
                case R.id.navigation_item_2:

                    menuItem.setChecked(true);
                    setNewRootFragment(R.id.navigation_item_2);

                    return true;
                case R.id.navigation_item_3:

                    menuItem.setChecked(true);
                    setNewRootFragment(R.id.navigation_item_3);

                    return true;
                case R.id.navigation_item_night:

//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    setNewRootFragment(R.id.navigation_item_night);

                    return true;
//                case R.id.navigation_item_day:

//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

//                    return true;
                default:
                    return true;
            }
        }
    }

    private void setNewRootFragment(int id) {
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideFragment(transaction);
        Fragment fragment = null;
        switch (id){
            case R.id.navigation_item_1:
                myFragment = (MyFragment) mFragmentManager.findFragmentByTag("myfragment");
                if(null == myFragment){
                    myFragment = new MyFragment();
                    transaction.add(R.id.contentLayout,myFragment,"myfragment");
                }else{
                    transaction.show(myFragment);
                }
                mFab.setVisibility(View.VISIBLE);
                break;

            case R.id.navigation_item_2:
                otherFragment = (OtherFragment) mFragmentManager.findFragmentByTag("otherFragment");
                if(null == otherFragment){
                    otherFragment = new OtherFragment();
                    transaction.add(R.id.contentLayout,otherFragment,"otherFragment");
                }else{
                    transaction.show(otherFragment);
                }
                mFab.setVisibility(View.GONE);
                break;

            case R.id.navigation_item_3:
                aboutAuthorFragment = (AboutAuthorFragment) mFragmentManager.findFragmentByTag("aboutAuthorFragment");
                if(null == aboutAuthorFragment){
                    aboutAuthorFragment = new AboutAuthorFragment();
                    transaction.add(R.id.contentLayout,aboutAuthorFragment,"aboutAuthorFragment");
                }else{
                    transaction.show(aboutAuthorFragment);
                }
                mFab.setVisibility(View.GONE);
                break;

            case R.id.navigation_item_night:
                settingFragment = (SettingFragment) mFragmentManager.findFragmentByTag("settingFragment");
                if(null == settingFragment){
                    settingFragment = new SettingFragment();
                    transaction.add(R.id.contentLayout,settingFragment,"settingFragment");
                }else{
                    transaction.show(settingFragment);
                }
                mFab.setVisibility(View.GONE);
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (null != myFragment) {
            transaction.hide(myFragment);
        }
        if (null != otherFragment) {
            transaction.hide(otherFragment);
        }
        if (null != aboutAuthorFragment) {
            transaction.hide(aboutAuthorFragment);
        }
        if (null != settingFragment) {
            transaction.hide(settingFragment);
        }
    }

    @Override
    protected void initListener() {

    }

}
