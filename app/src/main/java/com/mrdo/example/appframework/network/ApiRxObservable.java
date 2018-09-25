package com.mrdo.example.appframework.network;

import com.mrdo.example.appframework.data.base.HttpResponse;
import com.mrdo.example.appframework.network.functions.ResultErrorFun;
import com.mrdo.example.appframework.network.functions.ServerErrorFun;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ApiRxObservable {

    public static <T> Observable getObservable(Observable<HttpResponse<T>> apiObservable) {
        Observable observable = apiObservable
                .map(new ServerErrorFun())
                .onErrorResumeNext(new ResultErrorFun<>())
//                .compose(ProgressUtils.applyProgressBar(activity, showProgress, msg))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    /**
     * 获取被监听者
     * 备注:网络请求Observable构建
     * data:网络请求参数
     * <h1>补充说明</h1>
     * 传入LifecycleProvider自动管理生命周期,避免内存溢出
     * 备注:需要继承RxActivity.../RxFragment...
     */
    public static <T> Observable getObservable(Observable<HttpResponse<T>> apiObservable, LifecycleProvider lifecycle) {
        //showLog(request );
        Observable<HttpResponse<T>> observable;

        if (lifecycle != null) {
            //随生命周期自动管理.eg:onCreate(start)->onStop(end)
            observable = apiObservable
                    .map(new ServerErrorFun())
                    .compose(lifecycle.bindToLifecycle())//需要在这个位置添加  随生命周期自动管理.eg:onCreate(start)->onStop(end)
                    .onErrorResumeNext(new ResultErrorFun<>())
//                    .compose(ProgressUtils.applyProgressBar(activity, showProgress, msg))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            observable = getObservable(apiObservable);
        }
        return observable;
    }

    /**
     * 获取被监听者
     * 备注:网络请求Observable构建
     * data:网络请求参数
     * <h1>补充说明</h1>
     * 传入LifecycleProvider<ActivityEvent>手动管理生命周期,避免内存溢出
     * 备注:需要继承RxActivity,RxAppCompatActivity,RxFragmentActivity
     *
     */
    public static <T> Observable getObservable(Observable<HttpResponse<T>> apiObservable, LifecycleProvider<ActivityEvent> lifecycle, ActivityEvent event) {
        // showLog(request);
        Observable observable;
        if (lifecycle != null) {
            //手动管理移除监听生命周期.eg:ActivityEvent.STOP
            observable = apiObservable
                    .map(new ServerErrorFun())
                    .compose(lifecycle.bindUntilEvent(event))//需要在这个位置添加
                    .onErrorResumeNext(new ResultErrorFun<>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            observable = getObservable(apiObservable);
        }
        return observable;
    }


    /**
     * 获取被监听者
     * 备注:网络请求Observable构建
     * data:网络请求参数
     * <h1>补充说明</h1>
     * 传入LifecycleProvider<FragmentEvent>手动管理生命周期,避免内存溢出
     * 备注:需要继承RxFragment,RxDialogFragment
     *
     */
    public static <T> Observable getObservable(Observable<HttpResponse<T>> apiObservable, LifecycleProvider<FragmentEvent> lifecycle, FragmentEvent event) {
        //  showLog(request);
        Observable observable;
        if (lifecycle != null) {
            //手动管理移除监听生命周期.eg:FragmentEvent.STOP
            observable = apiObservable
                    .map(new ServerErrorFun())
                    .compose(lifecycle.bindUntilEvent(event))//需要在这个位置添加
                    .onErrorResumeNext(new ResultErrorFun<>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            observable = getObservable(apiObservable);
        }
        return observable;
    }
}
