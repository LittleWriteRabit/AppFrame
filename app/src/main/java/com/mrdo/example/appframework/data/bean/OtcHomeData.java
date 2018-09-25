package com.mrdo.example.appframework.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by dulijie on 2018/8/31.
 */
public class OtcHomeData implements Parcelable {

    /**
     * coins : 同接口2返参的obj
     * legaltenders : 同接口38返参的obj
     * countrys : 同接口3返参的obj
     * paymentchannels : 同接口26返参的obj
     */

    public List<IssuedCoinBean> coins;
    public List<CountrylistBean> legaltenders;
    public List<CountrylistBean> countrys;
    public List<PayTypeBean> paymentchannels;

    public OtcHomeData() {
    }


    protected OtcHomeData(Parcel in) {
        coins = in.createTypedArrayList(IssuedCoinBean.CREATOR);
        legaltenders = in.createTypedArrayList(CountrylistBean.CREATOR);
        countrys = in.createTypedArrayList(CountrylistBean.CREATOR);
        paymentchannels = in.createTypedArrayList(PayTypeBean.CREATOR);
    }

    public static final Creator<OtcHomeData> CREATOR = new Creator<OtcHomeData>() {
        @Override
        public OtcHomeData createFromParcel(Parcel in) {
            return new OtcHomeData(in);
        }

        @Override
        public OtcHomeData[] newArray(int size) {
            return new OtcHomeData[size];
        }
    };

    @Override
    public String toString() {
        return "OtcHomeData{" +
                "coins=" + coins +
                ", legaltenders=" + legaltenders +
                ", countrys=" + countrys +
                ", paymentchannels=" + paymentchannels +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(coins);
        dest.writeTypedList(legaltenders);
        dest.writeTypedList(countrys);
        dest.writeTypedList(paymentchannels);
    }
}