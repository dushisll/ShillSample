package sample.shillsample.utils;

import android.support.design.widget.TabLayout;
import android.view.View;

import sample.shillsample.BaseApp;

/**
 * Created by we on 2016/12/8.
 */

public class TabUtils {
    public static void dynamicSetTabLayoutMode(TabLayout tabLayout){
        int tabWidth = calculateTabWidth(tabLayout);
        int screenWidth = getScreenWidth();

        if(tabWidth <= screenWidth){
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        }else{
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }

    private static int calculateTabWidth(TabLayout tabLayout){
        int tabWidth = 0;
        for(int i = 0;i<tabLayout.getChildCount();i++){
            final View view = tabLayout.getChildAt(i);
            view.measure(0,0);
            tabWidth += view.getMeasuredWidth();
        }

        return tabWidth;
    }

    public static int getScreenWidth(){
        return BaseApp.getApplication().getApplicationContext().getResources()
                .getDisplayMetrics().widthPixels;
    }
}
