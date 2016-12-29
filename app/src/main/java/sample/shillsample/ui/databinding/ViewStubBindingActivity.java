package sample.shillsample.ui.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;

import sample.shillsample.R;
import sample.shillsample.databinding.ActivityDatabindingViewstubBinding;
import sample.shillsample.databinding.ViewStubBinding;
import sample.shillsample.ui.databinding.module.User;

/**
 * Created by we on 2016/12/20.
 */

public class ViewStubBindingActivity extends AppCompatActivity {

    ActivityDatabindingViewstubBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding_viewstub);

        binding.viewStub.setOnInflateListener(new ViewStub.OnInflateListener() {
            @Override
            public void onInflate(ViewStub viewStub, View view) {
                ViewStubBinding mbinding = DataBindingUtil.bind(view);
                User user = new User("shi","liang");
                mbinding.setUser(user);
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null ){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayUseLogoEnabled(true);
        }
    }

    public void InflateViewStub(View view){
        if(!binding.viewStub.isInflated()){
            binding.viewStub.getViewStub().inflate();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
