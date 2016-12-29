package sample.shillsample.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by we on 2016/12/8.
 */

public class Channel implements Parcelable,Serializable {
    private String channelName;
    private String channelId;
    private String channelType;
    private boolean channelSelect;
    private String channelIndex;
    private Boolean channelFixed;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public boolean isChannelSelect() {
        return channelSelect;
    }

    public void setChannelSelect(boolean channelSelect) {
        this.channelSelect = channelSelect;
    }

    public String getChannelIndex() {
        return channelIndex;
    }

    public void setChannelIndex(String channelIndex) {
        this.channelIndex = channelIndex;
    }

    public Boolean getChannelFixed() {
        return channelFixed;
    }

    public void setChannelFixed(Boolean channelFixed) {
        this.channelFixed = channelFixed;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.channelName);
        dest.writeString(this.channelId);
        dest.writeString(this.channelType);
        dest.writeByte(this.channelSelect ? (byte) 1 : (byte) 0);
        dest.writeString(this.channelIndex);
        dest.writeValue(this.channelFixed);
    }

    public Channel() {
    }

    protected Channel(Parcel in) {
        this.channelName = in.readString();
        this.channelId = in.readString();
        this.channelType = in.readString();
        this.channelSelect = in.readByte() != 0;
        this.channelIndex = in.readString();
        this.channelFixed = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Parcelable.Creator<Channel> CREATOR = new Parcelable.Creator<Channel>() {
        @Override
        public Channel createFromParcel(Parcel source) {
            return new Channel(source);
        }

        @Override
        public Channel[] newArray(int size) {
            return new Channel[size];
        }
    };
}
