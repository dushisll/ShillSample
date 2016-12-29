package sample.shillsample.ui.main;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import sample.shillsample.R;
import sample.shillsample.baseadapter.BaseRecyclerViewAdapter;
import sample.shillsample.baseadapter.OnItemClickListener;
import sample.shillsample.baseui.BaseActivity;
import sample.shillsample.ui.CircleProgress.CircleProgressActivity;
import sample.shillsample.ui.waveview.WaveViewActivity;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;

    private BaseRecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initData() {

//        mToolBar.setTitle("蠹石的Sample");
//        setSupportActionBar(mToolBar);
        setToolbar(mToolBar,"一些动画");
        mPresenter.initAdapter(mRecyclerview);
        mPresenter.getListData();
    }


    @Override
    protected void initListener() {
        mRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                if(position == 0){
                    startActivity(new Intent(MainActivity.this, CircleProgressActivity.class));
                }else if(position == 1){
                    startActivity(new Intent(MainActivity.this, WaveViewActivity.class));
                }

            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                Snackbar.make(mToolBar,position+"longclick",Snackbar.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void getListData(List<String> title) {
        mRecyclerViewAdapter.replaceAll(title);
    }

    @Override
    public void initAdapter(BaseRecyclerViewAdapter adapter) {
        mRecyclerViewAdapter = adapter;
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
