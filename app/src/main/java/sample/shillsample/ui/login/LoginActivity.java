package sample.shillsample.ui.login;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sample.shillsample.MyApp;
import sample.shillsample.R;
import sample.shillsample.baseui.BaseActivity;

/**
 * Created by we on 2016/12/22.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.render_bg)
    ImageView renderBg;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.login_weixin)
    Button loginWeixin;
    @BindView(R.id.login_qq)
    Button loginQq;
    @BindView(R.id.login_weibo)
    Button loginWeibo;
    @BindView(R.id.seekbar)
    SeekBar seekbar;

    @Override
    protected void initInjector() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {

        setToolbar(toolBar,"登录");

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.login_weixin, R.id.login_qq, R.id.login_weibo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_weixin:

                break;
            case R.id.login_qq:

                break;
            case R.id.login_weibo:

                break;
        }
    }
}
