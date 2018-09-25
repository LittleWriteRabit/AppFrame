package com.mrdo.example.appframework.network;

import retrofit2.Retrofit;


/**
 * Created by dulijie on 2018/9/5.
 * Retrofit Client
 * <p>
 * ApiClient.getInstance().getApiServices(ApiService.class,BaseURl)
 * ApiClient.getApiService(ApiService,HttpCommonUtils.getBaseUrl())
 */
public class ApiClient {
    private static ApiClient sApiClient;

    private ApiClient() {
    }

    public static ApiClient getInstance() {
        if (sApiClient == null) {
            synchronized (ApiClient.class) {
                if (sApiClient == null) {
                    sApiClient = new ApiClient();
                }
            }
        }
        return sApiClient;
    }

    public /*static*/ <T> T getApiService(Class<T> cls, String baseUrl) {
        Retrofit retrofit = RetrofitUtils.getRetrofitBuilder(baseUrl).build();
        return retrofit.create(cls);
    }

    public ApiService getApiService() {
        return getApiService(ApiService.class, HttpCommonUtils.getBaseUrl());
    }
}
