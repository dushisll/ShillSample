package sample.shill.mylibrary.commoninjector.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import sample.shill.mylibrary.BaseApp;
import sample.shill.mylibrary.commoninjector.modules.AppModule;

/**
 * Created by we on 2016/12/5.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Context proContext();
    void inject(BaseApp app);
}
