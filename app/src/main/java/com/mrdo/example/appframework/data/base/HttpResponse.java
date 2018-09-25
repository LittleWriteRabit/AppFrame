package com.mrdo.example.appframework.data.base;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dulijie on 2018/9/10.
 * 请求响应
 */
public class HttpResponse<T> implements Parcelable{

    /**
     * 响应码
     */
    public int responseCode;

    /**
     * 描述信息学
     */
    public String message;

    /**
     * 状态
     */
    public String status;

    /**
     * 数据对象
     */
    public T data;

    public boolean success = false;

    public HttpResponse() {
    }

    protected HttpResponse(Parcel in) {
        responseCode = in.readInt();
        message = in.readString();
        status = in.readString();
        success = in.readByte() != 0;
    }

    public static final Creator<HttpResponse> CREATOR = new Creator<HttpResponse>() {
        @Override
        public HttpResponse createFromParcel(Parcel in) {
            return new HttpResponse(in);
        }

        @Override
        public HttpResponse[] newArray(int size) {
            return new HttpResponse[size];
        }
    };

    /**
     * 是否成功
     * @return
     */
    public boolean isSuccess(){
        if(responseCode == 200){
            success = true;
        }
        return success;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(responseCode);
        dest.writeString(message);
        dest.writeString(status);
        dest.writeByte((byte) (success ? 1 : 0));
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "responseCode=" + responseCode +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", data=" + data +
                ", success=" + success +
                '}';
    }
}
