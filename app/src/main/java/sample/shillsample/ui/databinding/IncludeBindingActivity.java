package sample.shillsample.ui.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import sample.shillsample.R;
import sample.shillsample.databinding.ActivityDatabindingIncludeBinding;
import sample.shillsample.ui.databinding.listener.OkListener;
import sample.shillsample.ui.databinding.module.User;

/**
 * Created by we on 2016/12/19.
 */

public class IncludeBindingActivity extends AppCompatActivity implements OkListener{
    ActivityDatabindingIncludeBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = DataBindingUtil.setContentView(this, R.layout.activity_databinding_include);
        binding.setListener(this);
        binding.setOnText("ok");
        binding.layoutInput.etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                User user = new User(charSequence.toString(),"liang");
                binding.setUser(user);
            }

            @Override
            public void afterTextChanged(Editable editable) {

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickOK(View view) {
        Toast.makeText(this, "the btn clicked!", Toast.LENGTH_SHORT).show();
    }
}
