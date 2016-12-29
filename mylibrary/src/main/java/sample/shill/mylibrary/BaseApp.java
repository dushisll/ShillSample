package sample.shill.mylibrary;

import android.app.Application;

import sample.shill.mylibrary.commoninjector.components.AppComponent;
import sample.shill.mylibrary.commoninjector.components.DaggerAppComponent;
import sample.shill.mylibrary.commoninjector.modules.AppModule;

/**
 * Created by we on 2016/12/5.
 */

public class BaseApp extends Application {
    private static AppComponent mAppComponent;
    private static BaseApp baseApp;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApp = this;
        initComponent();
    }

    private void initComponent() {
        mAppComponent = DaggerAppComponent.builder()
                .build();
        mAppComponent.inject(this);
    }

    public static AppComponent getAppComponent(){
        return mAppComponent;
    }

    public static BaseApp getApplication(){
        return baseApp;
    }
}
