package sample.shill.mylibrary.commoninjector.components;

import android.support.v7.app.AppCompatActivity;

import dagger.Component;
import sample.shill.mylibrary.baseui.BaseActivity;
import sample.shill.mylibrary.commoninjector.PerActivity;
import sample.shill.mylibrary.commoninjector.modules.ActivityModule;

/**
 * Created by we on 2016/12/5.
 */

@PerActivity
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
}
