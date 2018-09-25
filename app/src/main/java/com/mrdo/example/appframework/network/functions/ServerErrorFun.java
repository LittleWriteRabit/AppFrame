package com.mrdo.example.appframework.network.functions;


import com.mrdo.example.appframework.data.base.HttpResponse;
import com.mrdo.example.appframework.network.ExceptionHandle;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by dulijie on 2018/9/11.
 */
public class ServerErrorFun<T> implements Function<HttpResponse<T>, HttpResponse<T>> {
    @Override
    public HttpResponse<T> apply(@NonNull HttpResponse<T> response) {
        if (!response.isSuccess()) {
            throw new ExceptionHandle.ServerException(response.responseCode, response.message);
        }
        return response;
    }
}
