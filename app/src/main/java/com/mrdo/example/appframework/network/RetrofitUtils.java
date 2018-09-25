package com.mrdo.example.appframework.network;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mrdo.example.appframework.BuildConfig;
import com.mrdo.example.appframework.utils.UIUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dulijie on 2018/9/11.
 */
public class RetrofitUtils {

    public static final int MAX_IDLE_CONNECTIONS = 5;   //这个池有5个空闲连接，
    public static final int KEEP_ALIVE_DURATION = 5;    //在5分钟的不活动之后将被驱逐。
    public static final int CONNECT_TIME_OUT = 10;      //连接超时时长x秒
    public static final int READ_TIME_OUT = 10;         //读数据超时时长x秒
    public static final int WRITE_TIME_OUT = 10;        //写数据接超时时长x秒

    public static OkHttpClient.Builder getOkHttpClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//            @Override
//            public void log(String message) {
//                try {
//                    LogUtils.e("OKHttp-----", URLDecoder.decode(message, "utf-8"));
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                    LogUtils.e("OKHttp-----", message);
//                }
//            }
//        });
//        //日志
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (BuildConfig.DEBUG) {
//            builder.addInterceptor(loggingInterceptor);
            builder.addInterceptor(HttpCommonUtils.getHttpLoggingInterceptor());
        }
        //緩存
        File cacheFile = new File(UIUtils.getContext().getCacheDir(), "responses");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 10); //100Mb

        return builder.connectionPool(new ConnectionPool(MAX_IDLE_CONNECTIONS, KEEP_ALIVE_DURATION, TimeUnit.MINUTES))
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
//                .addInterceptor(new HttpHeaderInterceptor())
//                .addNetworkInterceptor(new HttpCacheInterceptor())
                // https认证 如果要使用https且为自定义证书 可以去掉下面这两行注释，并自行配制证书。
                // .sslSocketFactory(SslContextFactory.getSSLSocketFactoryForTwoWay())
                // .hostnameVerifier(new SafeHostnameVerifier())
                .cache(cache);
    }

    public static Retrofit.Builder getRetrofitBuilder(String baseUrl) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        OkHttpClient okHttpClient = RetrofitUtils.getOkHttpClientBuilder().build();
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl);
    }
}
