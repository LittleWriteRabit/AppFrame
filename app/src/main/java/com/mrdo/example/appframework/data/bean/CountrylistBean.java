package com.mrdo.example.appframework.data.bean;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * @author dulijie  2018/8/31 16:31
 * @email dulijie@gichain.net
 * @description:TODO 国家列表
 */
public class  CountrylistBean implements Parcelable {

    public int id;
    public String countryCode;
    public String countryName;
    public String legalTenderCode;
    public String legalTenderName;
    public String legalTenderSign="￥";

    public CountrylistBean() {
    }

    protected CountrylistBean(Parcel in) {
        id = in.readInt();
        countryCode = in.readString();
        countryName = in.readString();
        legalTenderCode = in.readString();
        legalTenderName = in.readString();
        legalTenderSign = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(countryCode);
        dest.writeString(countryName);
        dest.writeString(legalTenderCode);
        dest.writeString(legalTenderName);
        dest.writeString(legalTenderSign);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CountrylistBean> CREATOR = new Creator<CountrylistBean>() {
        @Override
        public CountrylistBean createFromParcel(Parcel in) {
            return new CountrylistBean(in);
        }

        @Override
        public CountrylistBean[] newArray(int size) {
            return new CountrylistBean[size];
        }
    };

    @Override
    public String toString() {
        return "CountrylistBean{" +
                "id=" + id +
                ", countryCode='" + countryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", legalTenderCode='" + legalTenderCode + '\'' +
                ", legalTenderName='" + legalTenderName + '\'' +
                ", legalTenderSign='" + legalTenderSign + '\'' +
                '}';
    }
}