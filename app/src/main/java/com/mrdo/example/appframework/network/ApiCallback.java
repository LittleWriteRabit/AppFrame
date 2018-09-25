package com.mrdo.example.appframework.network;

import com.mrdo.example.appframework.data.base.HttpResponse;

import io.reactivex.disposables.Disposable;

/**
 * Created by dulijie on 2018/9/11.
 * 通用返回回调
 */
public abstract class ApiCallback<T> extends DefaultObserver<HttpResponse<T>> {

    public ApiCallback() {
    }

    public ApiCallback(String mTag) {
        super(mTag);
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        super.onSubscribe(disposable);
    }

    @Override
    public void onNext(HttpResponse<T> response) {
        super.onNext(response);
    }

    @Override
    public void onComplete() {
        super.onComplete();
    }
}
