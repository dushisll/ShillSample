package sample.shillsample.ui.main;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import sample.shillsample.baseadapter.BaseRecyclerViewAdapter;
import sample.shillsample.baseui.IPresenter;


/**
 * Created by we on 2016/12/3.
 */

public interface MainContract {
    interface View{
        void getListData(List<String> title);
        void initAdapter(BaseRecyclerViewAdapter adapter);
    }
    interface Presenter extends IPresenter<View> {
        void getListData();
        void initAdapter(RecyclerView recyclerView);
    }
}
