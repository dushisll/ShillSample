package sample.shillsample;


import com.samsung.android.sdk.multiwindow.SMultiWindow;

/**
 * Created by we on 2016/12/5.
 */

public class MyApp extends BaseApp{
    private SMultiWindow mMultiWindow = null;
    @Override
    public void onCreate() {
        super.onCreate();
        mMultiWindow = new SMultiWindow();
        try{
            mMultiWindow.initialize(getApplicationContext());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
