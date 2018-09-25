package com.mrdo.example.appframework.network;

import com.mrdo.example.appframework.data.base.HttpResponse;
import com.mrdo.example.appframework.data.bean.OtcHomeData;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by dulijie on 2018/9/5.
 * Retrofit ApiService
 */
public interface ApiService {

    @GET("login")
    Observable<HttpResponse<String>> login(@QueryMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("regist")
    Observable<HttpResponse<String>> regist(@FieldMap Map<String, Object> map);

    @GET("newc2c/homedata")
    Observable<HttpResponse<OtcHomeData>> homedata();
}
