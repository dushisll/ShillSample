package sample.shill.mylibrary.commoninjector.modules;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import sample.shill.mylibrary.commoninjector.PerActivity;

/**
 * Created by we on 2016/12/5.
 */
/*
@Module:作为实例对象的容器。 //提供依赖对象的实例
@Provides:标注能够提供实例化对象的方法。关键字，标明该方法提供依赖对象
@Component:作为桥梁，注入对象的通道。
@Inject：需要注入的方法
 */

@Module
public class ActivityModule {
    private static Activity mActivity;

    public ActivityModule(Activity activity){
        this.mActivity = activity;
    }

    @Provides
    @PerActivity
    public static Activity provideActivity(){
        return mActivity;
    }
}
