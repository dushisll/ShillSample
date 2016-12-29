package sample.shillsample.ui.mine.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sample.shillsample.Constant;
import sample.shillsample.MyApp;
import sample.shillsample.appmanager.ChannelManager;
import sample.shillsample.baseadapter.BaseFragmentPageAdapter;
import sample.shillsample.baseui.BasePresenter;
import sample.shillsample.bean.Channel;
import sample.shillsample.commoninjector.PerFragment;
import sample.shillsample.utils.ACache;

/**
 * Created by we on 2016/12/8.
 */

@PerFragment
public class MainFragmentPresenter extends BasePresenter<MainFragmentConstract.View> implements MainFragmentConstract.Presenter{

    @Inject
    public MainFragmentPresenter(){

    }

    @Override
    public void loadMainChannel() {
        Observable<List<Channel>>  observable =  Observable.create(new Observable.OnSubscribe<List<Channel>>() {
            @Override
            public void call(Subscriber<? super List<Channel>> subscriber) {
                ArrayList<Channel> channelTabList = (ArrayList<Channel>) ACache
                        .get(MyApp.getApplication().getApplicationContext())
                        .getAsObject(Constant.CHANNEL_MINE);

                if(channelTabList == null){
                    channelTabList = (ArrayList<Channel>) ChannelManager.loadChannel();
                    ACache.get(MyApp.getApplication().getApplicationContext())
                            .put(Constant.CHANNEL_MINE,channelTabList);
                }

                subscriber.onNext(channelTabList);
                subscriber.onCompleted();
            }
        }).compose(new Observable.Transformer<List<Channel>, List<Channel>>() {
            @Override
            public Observable<List<Channel>> call(Observable<List<Channel>> listObservable) {
                return listObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        });

        observable.subscribe(new Subscriber<List<Channel>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Channel> channels) {
                mView.loadMainChannel(channels);
            }
        });
    }

}
