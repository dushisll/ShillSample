package sample.shillsample.ui.mine.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import sample.shillsample.R;
import sample.shillsample.api.Api;
import sample.shillsample.api.HostType;
import sample.shillsample.baseadapter.BaseRecyclerViewAdapter;
import sample.shillsample.baseadapter.ViewHolderHelper;
import sample.shillsample.baseui.BasePresenter;
import sample.shillsample.bean.Girl;
import sample.shillsample.bean.GirlData;
import sample.shillsample.commoninjector.PerFragment;

/**
 * Created by we on 2016/12/9.
 */

@PerFragment
public class MainFashionFragmentPresenter extends BasePresenter<MainFashionFragmentConstract.View>
        implements MainFashionFragmentConstract.Presenter {

    @Inject
    public MainFashionFragmentPresenter(){
    }

    @Override
    public void initAdapter(RecyclerView recyclerView) {

        final BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter<GirlData>(mActivity, R.layout.item_girl) {

            @Override
            public void convert(ViewHolderHelper helper, GirlData girldata) {
                ImageView imageView = helper.getView(R.id.iv_girl);
                //Gilde 显示图片
                Glide.with(mActivity).load(girldata.getUrl())
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .placeholder(R.drawable.ic_image_loading)
                        .error(R.drawable.ic_empty_picture)
                        .centerCrop().override(1090, 1090*3/4)
                        .crossFade().into(imageView);
            }
        };

        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);//瀑布流
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.i("MainFashion","newState:"+newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int[] visibleItems = layoutManager.findLastVisibleItemPositions(null);
                int lastitem = Math.max(visibleItems[0],visibleItems[1]);
                Log.i("MainFashion","visibleItems =" + visibleItems);
                Log.i("MainFashion","lastitem =" + lastitem);
                Log.i("MainFashion","adapter.getItemCount() =" + adapter.getItemCount());
                if(lastitem +1 >= adapter.getItemCount()){
                    mView.loadingMore();
                }
            }
        });

        mView.initAdapter(adapter);
    }

    @Override
    public void getGirlData(int size,int page) {
        Observable<List<GirlData>> observable = Api.getDefault(HostType.GANK_GIRL_PHOTO)
                .getPhotoList(Api.getCacheControl(),size,page)
                .map(new Func1<Girl, List<GirlData>>() {
                    @Override
                    public List<GirlData> call(Girl girl) {
                        return girl.getResults();
                    }
                }).compose(new Observable.Transformer<List<GirlData>, List<GirlData>>() {
                    @Override
                    public Observable<List<GirlData>> call(Observable<List<GirlData>> listObservable) {
                        return listObservable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
        observable.subscribe(new Subscriber<List<GirlData>>() {
            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading("请稍后……");
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mView.showErrorTips("出错了");
            }

            @Override
            public void onNext(List<GirlData> girlDatas) {
                mView.getGirlData(girlDatas);
                mView.stopLoading();
            }
        });
    }
}
