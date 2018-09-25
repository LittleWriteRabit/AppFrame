package com.mrdo.example.appframework.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 出售或者购买币种种类
 */
public class IssuedCoinBean implements Parcelable {

    public String coinCode;
    public String name;

    public IssuedCoinBean() {
    }

    public IssuedCoinBean(String coinCode, String name) {
        this.coinCode = coinCode;
        this.name = name;
    }

    protected IssuedCoinBean(Parcel in) {
        coinCode = in.readString();
        name = in.readString();
    }

    public static final Creator<IssuedCoinBean> CREATOR = new Creator<IssuedCoinBean>() {
        @Override
        public IssuedCoinBean createFromParcel(Parcel in) {
            return new IssuedCoinBean(in);
        }

        @Override
        public IssuedCoinBean[] newArray(int size) {
            return new IssuedCoinBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(coinCode);
        dest.writeString(name);
    }

    @Override
    public String toString() {
        return "IssuedCoinBean{" +
                "coinCode='" + coinCode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
