package sample.shillsample.commoninjector.components;

import javax.inject.Singleton;

import dagger.Component;

import sample.shillsample.BaseApp;
import sample.shillsample.commoninjector.modules.AppModule;

/**
 * Created by we on 2016/12/5.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(BaseApp app);
}
