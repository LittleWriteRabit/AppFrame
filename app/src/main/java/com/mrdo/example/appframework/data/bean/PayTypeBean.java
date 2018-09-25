package com.mrdo.example.appframework.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author dulijie  2018/8/31 16:35
 * @email dulijie@gichain.net
 * @description:TODO 支付方式 过滤用
 */
public class PayTypeBean implements Parcelable {
    public String code;
    public String name;

    public PayTypeBean() {
    }

    protected PayTypeBean(Parcel in) {
        code = in.readString();
        name = in.readString();
    }

    public static final Creator<PayTypeBean> CREATOR = new Creator<PayTypeBean>() {
        @Override
        public PayTypeBean createFromParcel(Parcel in) {
            return new PayTypeBean(in);
        }

        @Override
        public PayTypeBean[] newArray(int size) {
            return new PayTypeBean[size];
        }
    };

    @Override
    public String toString() {
        return "PayTypeBean{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeString(name);
    }
}