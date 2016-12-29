package sample.shillsample.appmanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sample.shillsample.MyApp;
import sample.shillsample.R;
import sample.shillsample.bean.Channel;

/**
 * Created by we on 2016/12/8.
 */

public class ChannelManager {

    public static List<Channel> loadChannel(){
        List<String> channelName = Arrays.asList(MyApp.getApplication().getApplicationContext()
                .getResources().getStringArray(R.array.main_channel));
        ArrayList<Channel> channels = new ArrayList<>();
        for(int i=0;i<channelName.size();i++){
            Channel chanel = new Channel();
            chanel.setChannelName(channelName.get(i));
            chanel.setChannelSelect(i<=5);
            channels.add(chanel);
        }

        return channels;
    }
}
