package com.mrdo.example.appframework.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.Window;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by dulijie on 2018/9/10.
 * BaseDialog
 */
public abstract class BaseDialog extends Dialog {

    protected CompositeDisposable mCompositeDisposable;
    protected Context context;

    /**
     * RXjava取消注册，以避免内存泄露
     */
    public void onUnsubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    /**
     * 添加需要被销毁的发出者
     *
     * @param disposable
     */
    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }


    public BaseDialog(Context context) {
        super(context);
        this.context = context;
        Window window = getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        Window window = getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
    }


    protected BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
        Window window = getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideLayoutId());
        ButterKnife.bind(this, this);
    }

    public abstract @LayoutRes
    int provideLayoutId();

    @Override
    public void dismiss() {
        super.dismiss();
        onUnsubscribe();
    }

    @Override
    public void show() {
        super.show();
    }
}