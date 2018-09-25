package com.mrdo.example.appframework.network;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.blankj.utilcode.util.LogUtils;
import com.mrdo.example.appframework.ui.dialog.ProgressDialogs;
import com.mrdo.example.appframework.utils.UIUtils;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * 添加进度条
 */
public class ProgressUtils {

    public static <T> ObservableTransformer<T, T> applyProgressBar(
            @NonNull final Activity activity, final boolean show, final String msg) {
        final WeakReference<Activity> activityWeakReference = new WeakReference<>(activity);
        final ProgressDialogs progressDialogs = new ProgressDialogs(activityWeakReference.get());
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        LogUtils.d("[[===========doOnSubscribe1===========");
                        if (progressDialogs != null && !progressDialogs.isShowing() && show) {
                            progressDialogs.setText(msg);
                            progressDialogs.show();
                        }
                    }
                }).doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.d("[[===========doOnTerminate===========");
                        Activity context;
                        if ((context = activityWeakReference.get()) != null
                                && !context.isFinishing()
                                && progressDialogs != null
                                && progressDialogs.isShowing()) {
                            progressDialogs.dismiss();
                        }
                    }
                }).doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        LogUtils.d("[[===========doOnSubscribe2===========");
                        /*Activity context;
                        if ((context = activityWeakReference.get()) != null
                                && !context.isFinishing()) {
                            dialogUtils.dismissProgress();
                        }*/
                    }
                }).doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.d("[[===========doAfterTerminate===========");
                    }
                });
            }
        };
    }

    /**
     * 是否显示进度条
     *
     * @param activity
     * @param resId
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> applyProgressBar(
            @NonNull final Activity activity, boolean show, @StringRes int resId) {
        return applyProgressBar(activity, show, UIUtils.getString(resId));
    }

    /**
     * 显示制定内容进度条
     *
     * @param activity
     * @param resId
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> applyProgressBar(
            @NonNull final Activity activity, @StringRes int resId) {
        return applyProgressBar(activity, true, UIUtils.getString(resId));
    }

    /**
     * 显示默认进度条
     *
     * @param activity
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> applyProgressBar(
            @NonNull final Activity activity) {
        return applyProgressBar(activity, true, "");
    }

    /**
     * 不现实进度条
     *
     * @param activity
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> applyNoProgressBar(
            @NonNull final Activity activity) {
        return applyProgressBar(activity, false, "");
    }
}