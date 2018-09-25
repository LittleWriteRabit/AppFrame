package com.mrdo.example.appframework.network.functions;

import com.mrdo.example.appframework.network.ExceptionHandle;
import com.orhanobut.logger.Logger;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by dulijie on 2018/9/11.
 */
public class ResultErrorFun<T> implements Function<Throwable, Observable<T>> {
    @Override
    public Observable<T> apply(Throwable throwable) throws Exception {
        //打印具体错误
        Logger.e("HttpResultFunction:" + throwable);
        return Observable.error(ExceptionHandle.handleException(throwable));
    }
}