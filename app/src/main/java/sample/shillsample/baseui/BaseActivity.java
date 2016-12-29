package sample.shillsample.baseui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import butterknife.ButterKnife;
import sample.shillsample.BaseApp;
import sample.shillsample.basemanager.AppManager;
import sample.shillsample.commoninjector.components.ActivityComponent;
import sample.shillsample.commoninjector.components.AppComponent;
import sample.shillsample.commoninjector.components.DaggerActivityComponent;

/**
 * Created by we on 2016/12/3.
 */

public abstract class BaseActivity<T extends IPresenter> extends AppCompatActivity{

    @Inject
    public T mPresenter;
    protected ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setConfigBeforeContentView();
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        setupActivityComponent(BaseApp.getAppComponent());
        initInjector();
        init();
    }

    protected abstract void initInjector();

    protected void setupActivityComponent(AppComponent appComponent){
        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .build();
    }

    private void init() {
        if(mPresenter != null){
            mPresenter.attachView(this,this);
        }
        initData();
        initListener();
    }

    protected abstract int getLayoutId();
    protected abstract void initData();
    protected abstract void initListener();

    protected void setConfigBeforeContentView(){
        AppManager.getAppManager().addActivity(this);
    }

    protected void setToolbar(Toolbar toolbar, String title){
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.detachView();
        }
        AppManager.getAppManager().finishActivity(this);
    }
}
