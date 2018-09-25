package com.mrdo.example.appframework.network;


import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.mrdo.example.appframework.network.retrofit.RxActionManagerImpl;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by dulijie on 2018/9/11.
 * 默认返回回调
 */
public abstract class DefaultObserver<T> implements Observer<T> {

    private String mTag;

    public DefaultObserver() {
    }

    public DefaultObserver(String mTag) {
        this.mTag = mTag;
    }

    public void setTag(String mTag) {
        this.mTag = mTag;
    }

    @Override
    public void onSubscribe(Disposable disposable) {
//        LogUtils.d("[[===========onSubscribe===========");
//        if (!NetworkUtils.isConnected()) {
//            onError(new ExceptionHandle.ResponseThrowable(e,ExceptionHandle.ERROR.UNKNOWN));
//            disposable.dispose();
//            return;
//        }
        if (!TextUtils.isEmpty(mTag)) {
            RxActionManagerImpl.getInstance().add(mTag, disposable);
        }
        onStart(disposable);
    }

    @Override
    public void onNext(T t) {
        LogUtils.d("[[===========onNext===========");
        if (!TextUtils.isEmpty(mTag)) {
            RxActionManagerImpl.getInstance().remove(mTag);
        }
        onSuccess(t);
    }

    @Override
    public void onError(Throwable throwable) {
        LogUtils.d("[[===========onError==========="+throwable.getMessage());
        LogUtils.d("[[===========onError==========="+throwable.toString());
        RxActionManagerImpl.getInstance().remove(mTag);
        if (throwable instanceof Exception) {
            //访问获得对应的Exception
            onError(ExceptionHandle.handleException(throwable));
        } else {
            //将Throwable 和 未知错误的status code返回
            onError(new ExceptionHandle.ResponseThrowable(throwable, ExceptionHandle.ERROR.UNKNOWN));
        }
    }

    @Override
    public void onComplete() {
        LogUtils.d("[[===========onComplete===========");
    }

    protected abstract void onStart(Disposable disposable);

    protected abstract void onError(ExceptionHandle.ResponseThrowable responseThrowable);

    protected abstract void onSuccess(T t);

}
