package sample.shillsample.ui.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayMap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import sample.shillsample.R;
import sample.shillsample.observableBinding;
import sample.shillsample.ui.databinding.module.ObservableUser;
import sample.shillsample.ui.databinding.module.PlainUser;

/**
 * Created by we on 2016/12/19.
 */

public class ObservableBindingActivity extends AppCompatActivity {

    private ObservableUser user = new ObservableUser();
    private PlainUser plainUser = new PlainUser();
    private ObservableArrayMap<String, Object> mapUser = new ObservableArrayMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        observableBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding_observable);
        setMyName(null);

        binding.setUser(user);
        binding.setPlainUser(plainUser);
        binding.setMapUser(mapUser);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null ){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayUseLogoEnabled(true);
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

    public void setOtherName(View view){
        user.setmFirstName("shi");
        user.setmLastName("liang");

        plainUser.firstName.set("shi");
        plainUser.lastName.set("liang");
        plainUser.age.set(29);

        mapUser.put("firstname","shi");
        mapUser.put("lastname","liang");
        mapUser.put("age",17);
    }

    public void setMyName(View view){
        user.setmFirstName("du");
        user.setmLastName("shi");

        plainUser.firstName.set("du");
        plainUser.lastName.set("shi");
        plainUser.age.set(24);

        mapUser.put("firstname","du");
        mapUser.put("lastname","shi");
        mapUser.put("age",25);
    }
}
