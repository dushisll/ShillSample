package sample.shillsample.ui.databinding;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import sample.shillsample.R;
import sample.shillsample.baseui.BaseActivity;

/**
 * Created by we on 2016/12/19.
 */

public class MainActivity extends BaseActivity{
    @BindView(R.id.toolBar)
    Toolbar mToolbar;
    @Override
    protected void initInjector() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_databinding_main;
    }

    @Override
    protected void initData() {
        setToolbar(mToolbar,"DataBinding Sample");
    }

    @Override
    protected void initListener() {

    }

    public void openBaseSample(View view){
        startActivity(new Intent(this,BaseSampleActivity.class));
    }

    public void openCustomBinding(View view){
        startActivity(new Intent(this,CustomBindingActivity.class));
    }

    public void openInclude(View view){
        startActivity(new Intent(this,IncludeBindingActivity.class));
    }

    public void openCollections(View view){
        startActivity(new Intent(this,CollectionsBindingActivity.class));
    }

    public void openResources(View view){
        startActivity(new Intent(this,ResourcesBindingActivity.class));
    }

    public void openObservable(View view){
        startActivity(new Intent(this,ObservableBindingActivity.class));
    }

    public void openViewWithIDs(View view){
        startActivity(new Intent(this,ViewWithIdBindingActivity.class));
    }

    public void openViewStub(View view){
        startActivity(new Intent(this,ViewStubBindingActivity.class));
    }

    public void openDynamicVariables(View view){
        startActivity(new Intent(this,DynamicBindingActivity.class));
    }

}
