package sample.shillsample.commoninjector.components;

import dagger.Component;
import sample.shillsample.commoninjector.PerActivity;
import sample.shillsample.commoninjector.modules.ActivityModule;
import sample.shillsample.ui.main.MainActivity;
import sample.shillsample.ui.mine.MyActivity;

/**
 * Created by we on 2016/12/5.
 */

@PerActivity
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
