package com.mrdo.example.appframework.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mrdo.example.appframework.R;
import com.trello.rxlifecycle2.components.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by dulijie on 2018/9/10.
 */
public abstract class BaseFragment<T extends BasePresenter> extends RxFragment {

    protected T mPresenter;
    private View rootView;
    protected Activity mActivity;
    protected Unbinder unBinder;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mListener != null) {
            mListener.onCreate(savedInstanceState);
        }
        mPresenter = createPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(provideContentViewId(), container, false);
        }
        unBinder = ButterKnife.bind(this, rootView);
        init();
        if (!setFullScreen()) {
            setStatusView();
        }
        getSaveInstanceState(savedInstanceState);
        return rootView;
    }

    /**
     * 用来判断是否需要状态栏空间 因为有界面不需要填充
     * 比如首页头部是图片 如果有类似情况 重写此方法 返回true即可
     *
     * @return
     */
    protected boolean setFullScreen() {
        return false;
    }

    protected void setStatusView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            rootView.setPadding(0, getStatusBarHeight(), 0, 0);
        }
    }

    /**
     * View的处理事件 比如RecyclerView得布局管理器添加等
     * 如果不是延迟加载可以在此处加载数据(网络请求等)
     */
    protected abstract void init();

    /**
     * 获取Fragment的保存状态 用于列表得位置记录等
     */
    protected void getSaveInstanceState(Bundle savedInstanceState) {
    }

    /**
     * 用于创建Presenter和判断是否使用MVP模式(由子类实现)
     *
     * @return
     */
    protected abstract T createPresenter();

    /**
     * 得到当前界面的布局文件id(由子类实现)
     *
     * @return
     */
    protected abstract int provideContentViewId();

    public Toolbar initToolBar(View view, String title) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        TextView toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setText(title);
        return toolbar;
    }

    /**
     * 利用反射获取状态栏高度
     *
     * @return
     */
    protected int getStatusBarHeight() {
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 回调函数
     */
    public LifecycleListener mListener;

    public void setOnLifeCycleListener(LifecycleListener listener) {
        mListener = listener;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mListener != null) {
            mListener.onStart();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mListener != null) {
            mListener.onRestart();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mListener != null) {
            mListener.onRestart();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mListener != null) {
            mListener.onRestart();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mListener != null) {
            mListener.onDestroy();
        }
        //移除view绑定
        if (unBinder != null) {
            unBinder.unbind();
        }
    }
}
