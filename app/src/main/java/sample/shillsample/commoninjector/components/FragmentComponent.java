package sample.shillsample.commoninjector.components;

import android.app.Activity;

import dagger.Component;
import sample.shillsample.commoninjector.PerFragment;
import sample.shillsample.commoninjector.modules.FragmentModule;
import sample.shillsample.ui.mine.fragment.MainFashionFragment;
import sample.shillsample.ui.mine.fragment.MainFragment;
import sample.shillsample.ui.mine.fragment.MainFragmentPresenter;

/**
 * Created by we on 2016/12/7.
 */

@PerFragment
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();

    void inject(MainFragment mainFragment);
    void inject(MainFashionFragment mainFashionFragment);
}
