package sample.shillsample.ui.CircleProgress;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sample.shillsample.R;
import sample.shillsample.baseui.BaseActivity;
import sample.shillsample.widgets.CircleProgress;
import sample.shillsample.widgets.UpdateCircleProgress;

/**
 * Created by we on 2016/12/12.
 */

public class CircleProgressActivity extends BaseActivity {


    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.circleprogress)
    CircleProgress circleprogress;
    @BindView(R.id.circleprogress2)
    UpdateCircleProgress circleprogress2;

    @Override
    protected void initInjector() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_circleprogress;
    }

    @Override
    protected void initData() {
        setToolbar(toolBar, "CircleProgress");
        circleprogress.addProgress(0);
        circleprogress2.addProgress(0);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    int progress = 0;
    private Handler progressHandler = new Handler();

    private Runnable progressRunnable = new Runnable() {

        @Override
        public void run() {
            progress++;
            if (progress <= 360) {
                circleprogress.addProgress(progress);
                circleprogress2.addProgress(progress);
                progressHandler.postDelayed(progressRunnable, 200);
            }
        }
    };


    @OnClick(R.id.btn_start)
    public void onClick() {
        progressHandler.post(progressRunnable);
    }

}
