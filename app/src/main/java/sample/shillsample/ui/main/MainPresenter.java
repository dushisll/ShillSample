package sample.shillsample.ui.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import sample.shillsample.R;
import sample.shillsample.baseadapter.BaseRecyclerViewAdapter;
import sample.shillsample.baseadapter.ViewHolderHelper;
import sample.shillsample.baseui.BasePresenter;
import sample.shillsample.commoninjector.PerActivity;

/**
 * Created by we on 2016/12/3.
 */

@PerActivity
public class MainPresenter extends BasePresenter<MainContract.View> implements  MainContract.Presenter{

    @Inject
    public MainPresenter(){

    }

    private String []ActivityArray = {"几种环形进度条","WaveView几种实现方式"};

    @Override
    public void getListData() {
        List<String> list = new ArrayList<>();
        for(int i = 0;i<ActivityArray.length;i++){
            list.add(ActivityArray[i]);
        }
        mView.getListData(list);
    }

    @Override
    public void initAdapter(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        BaseRecyclerViewAdapter mRecyclerViewAdapter = new BaseRecyclerViewAdapter<String>(mActivity, R.layout.item_main) {
            @Override
            public void convert(ViewHolderHelper helper, String s) {
                helper.setText(R.id.tv_title,s);
            }
        };

        recyclerView.setAdapter(mRecyclerViewAdapter);
        mView.initAdapter(mRecyclerViewAdapter);
    }
}
