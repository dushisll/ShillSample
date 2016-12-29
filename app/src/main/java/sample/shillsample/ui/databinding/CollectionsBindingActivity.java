package sample.shillsample.ui.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sample.shillsample.CollectionBinding;
import sample.shillsample.R;

/**
 * Created by we on 2016/12/19.
 */

public class CollectionsBindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CollectionBinding bingding = DataBindingUtil.setContentView(this, R.layout.activity_databinding_collections);

        String []a = new String[]{"a","b","c","d"};
        List<String> list = new ArrayList<>();
        SparseArray<String> sparse = new SparseArray<>();
        String key = "firstName";
        int index = 0;

        for(int i = 0;i<a.length;i++){
            list.add(a[i]);
            sparse.put(i,a[i]);
        }

        Map<String,String> map = new HashMap<>();
        map.put(key,"liang");

        bingding.setIndex(index);
        bingding.setKey(key);
        bingding.setList(list);
        bingding.setSparse(sparse);
        bingding.setMap(map);

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
}
