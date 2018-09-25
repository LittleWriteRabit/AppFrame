package com.mrdo.example.appframework.network.retrofit;

import io.reactivex.disposables.Disposable;

/**
 * Created by dulijie on 2018/9/11.
 * 取消订阅使用
 */
public interface RxActionManager<T> {
    /**
     * 添加Tag
     *
     * @param tag
     * @param disposable
     */
    void add(T tag, Disposable disposable);

    /**
     * 删除Tag
     *
     * @param tag
     */
    void remove(T tag);

    /**
     * 取消Tag 请求
     *
     * @param tag
     */
    void cancel(T tag);

    /**
     * 取消全部
     */
    void cancelAll();
}
