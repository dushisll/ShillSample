package sample.shillsample.baseui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

import javax.inject.Inject;

import butterknife.ButterKnife;
import sample.shillsample.BaseApp;
import sample.shillsample.commoninjector.components.AppComponent;
import sample.shillsample.commoninjector.components.DaggerFragmentComponent;
import sample.shillsample.commoninjector.components.FragmentComponent;
import sample.shillsample.commoninjector.modules.FragmentModule;

/**
 * Created by we on 2016/12/5.
 */

public abstract class BaseFragment<T extends IPresenter> extends Fragment {
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    protected Context mActivity;
    //是否可见状态
    private boolean isVisible;
    //View已经初始化完成
    private boolean isPrepared;
    //是否第一次加载完
    private boolean isFirstLoad = true;

    @Inject
    protected T mPresenter;
    protected FragmentComponent mFragmentComponent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        isFirstLoad = true;

        View view = inflater.inflate(getlayoutId(),container,false);
        ButterKnife.bind(this,view);
        isPrepared = true;

        setupActivityComponent(BaseApp.getAppComponent(),new FragmentModule(this));
        initInjector();

        if(mPresenter != null){
            mPresenter.attachView(this,mActivity);
        }
        //初始化事件和获取数据, 在此方法中获取数据不是懒加载模式
        init();
        //在此方法中获取数据为懒加载模式,如不需要懒加载,请在initEventAndData获取数据
        initLazyLoad();
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(STATE_SAVE_IS_HIDDEN,isHidden());
    }

    @Override
    public void onAttach(Context context) {
        this.mActivity = context;
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
            try{
                Field childFragmentManager = android.support.v4.app.Fragment.class
                        .getDeclaredField("mChildFragmentManager");
                childFragmentManager.setAccessible(true);
                childFragmentManager.set(this,null);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){
            isVisible = true;
            onVisible();
        }else{
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible(){
        initLazyLoad();
    }

    protected void onInvisible(){}

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            isVisible = true;
            onVisible();
        }else{
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initLazyLoad() {
        if(!isPrepared || !isVisible || !isFirstLoad) return;
        isFirstLoad = false;
        lazyLoadData();
    }

    private void init() {
        initData();
        initListener();
    }

    private void setupActivityComponent(AppComponent appComponent, FragmentModule fragmentModule) {
        mFragmentComponent = DaggerFragmentComponent.builder().appComponent(appComponent)
                .fragmentModule(fragmentModule)
                .build();
    }

    protected abstract int getlayoutId();
    protected abstract void initInjector();
    protected abstract void initData();
    protected  abstract void initListener();
    protected abstract void lazyLoadData();
}
