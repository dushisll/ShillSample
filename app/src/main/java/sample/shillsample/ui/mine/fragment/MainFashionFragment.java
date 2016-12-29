package sample.shillsample.ui.mine.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import sample.shillsample.R;
import sample.shillsample.baseadapter.BaseRecyclerViewAdapter;
import sample.shillsample.baseui.BaseFragment;
import sample.shillsample.bean.GirlData;

/**
 * Created by we on 2016/12/9.
 */

public class MainFashionFragment extends BaseFragment<MainFashionFragmentPresenter> implements
        MainFashionFragmentConstract.View,
        SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;

    private BaseRecyclerViewAdapter adapter;
    private static int size = 20;
    private int mStartPage = 0;

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_mainfashion;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initData() {
        swiperefreshlayout.setOnRefreshListener(this);
        mPresenter.initAdapter(recyclerview);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void lazyLoadData() {
        swiperefreshlayout.setRefreshing(true);
        mPresenter.getGirlData(size,mStartPage);
    }

    @Override
    public void onRefresh() {
        adapter.getPageBean().setRefresh(true);
        mStartPage = 0;
        swiperefreshlayout.setRefreshing(true);
        mPresenter.getGirlData(size,mStartPage);
    }

    @Override
    public void initAdapter(BaseRecyclerViewAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void getGirlData(List<GirlData> girls) {
        if(girls != null){
            if(adapter.getPageBean().isRefresh()){
                swiperefreshlayout.setRefreshing(false);
                adapter.replaceAll(girls);
            }else{
                if(girls.size()>0){
                    adapter.addAll(girls);
                }
            }
        }

    }

    @Override
    public void showLoading(String title) {
        swiperefreshlayout.setRefreshing(true);
    }

    @Override
    public void stopLoading() {
        swiperefreshlayout.setRefreshing(false);
    }

    @Override
    public void showErrorTips(String msg) {
        swiperefreshlayout.setRefreshing(false);
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadingMore() {
        adapter.getPageBean().setRefresh(false);
        mStartPage += 1;
        mPresenter.getGirlData(size,mStartPage);
    }

}
