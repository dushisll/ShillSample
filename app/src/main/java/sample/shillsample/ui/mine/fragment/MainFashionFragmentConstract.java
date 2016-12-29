package sample.shillsample.ui.mine.fragment;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import sample.shillsample.baseadapter.BaseRecyclerViewAdapter;
import sample.shillsample.baseui.IPresenter;
import sample.shillsample.bean.Girl;
import sample.shillsample.bean.GirlData;

/**
 * Created by we on 2016/12/9.
 */

public interface MainFashionFragmentConstract {
    interface  View{
        void initAdapter(BaseRecyclerViewAdapter adapter);
        void getGirlData(List<GirlData> girls);

        void showLoading(String title);
        void stopLoading();
        void showErrorTips(String msg);
        void loadingMore();
    }

    interface  Presenter extends IPresenter<View> {
        void initAdapter(RecyclerView recyclerView);

        void getGirlData(int size,int page);
    }

}
