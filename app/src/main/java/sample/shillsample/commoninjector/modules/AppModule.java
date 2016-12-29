package sample.shillsample.commoninjector.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by we on 2016/12/5.
 */

@Module
public class AppModule {
    private Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    public Application provideApplication(){
        return mApplication;
    }
}
