package sample.shillsample.ui.waveview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import sample.shillsample.R;
import sample.shillsample.baseui.BaseActivity;
import sample.shillsample.widgets.MySinkingView;
import sample.shillsample.widgets.WaveViewByCos;

/**
 * Created by we on 2016/12/20.
 */

public class WaveViewActivity extends BaseActivity {

    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.waveviewbycos)
    WaveViewByCos waveviewbycos;
    @BindView(R.id.sinkView)
    MySinkingView sinkView;

    @Override
    protected void initInjector() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_waveview;
    }

    @Override
    protected void initData() {
        setToolbar(toolBar, "WaveView的三种实现");
        localTest();


    }

    private void localTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                while (true) {
                    waveviewbycos.setProgress(50);
//                }
            }
        }).start();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        progress = 0;
        handler.sendEmptyMessageDelayed(0x01, 1000);
    }

    private float progress;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x01:
                    progress += 0.01;
                    if (progress <= 0.5) {
                        sinkView.setPercent(progress);
                        sendEmptyMessageDelayed(0x01, 100);
                    }
                    break;
            }
        }
    };

}
