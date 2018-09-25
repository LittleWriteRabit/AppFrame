package com.mrdo.example.appframework.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.afollestad.appthemeengine.ATE;
import com.mrdo.example.appframework.MyApp;
import com.mrdo.example.appframework.R;
import com.mrdo.example.appframework.utils.Helpers;
import com.mrdo.example.appframework.utils.ScreenAdapter;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by dulijie on 2018/9/10.
 * BaseActivity  AppTheme相关请使用 {@link BaseThemeActivity}
 */
public abstract class BaseActivity<T extends BasePresenter> extends RxAppCompatActivity {

    protected Activity mActivity;
    private static long mPreTime;
    protected Unbinder unBinder;
    protected Context mContext;
    protected T mvpPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        if (applyATE()) {
//            ATE.preApply(this, this.getATEKey());
//        }
        if (transStatusBar()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
        if (fullScreen()) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        super.onCreate(savedInstanceState);
//        if (applyATE()) {
//            this.updateTime = System.currentTimeMillis();
//        }
        ScreenAdapter.setCustomDensity(this, MyApp.sApp);
        setContentView(provideContentViewId());
        mvpPresenter = createPresenter();
        baseInitData(savedInstanceState);
        initBundleData();
        init();
    }

    /**
     * 初始化应用程序，设置一些初始化数据,获取数据等操作
     */
    protected abstract void init();

    /**
     * 得到当前界面的布局文件id(由子类实现)
     *
     * @return
     */
    protected abstract @LayoutRes
    int provideContentViewId();

    /**
     * 用于创建Presenter和判断是否使用MVP模式(由子类实现)
     *
     * @return 返回Presenter
     */
    protected abstract T createPresenter();

    /**
     * 透明状态栏
     *
     * @return
     */
    protected boolean transStatusBar() {
        return false;
    }

    /**
     * 是否全屏
     *
     * @return
     */
    protected boolean fullScreen() {
        return false;
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        unBinder = ButterKnife.bind(this);
        mActivity = this;
    }

    protected void baseInitData(Bundle savedInstanceState) {
        if (mListener != null) {
            mListener.onCreate(savedInstanceState);
        }
        mContext = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
//        if (applyATE()) {
//            ATE.apply(this, this.getATEKey());
//        }
        if (mListener != null) {
            mListener.onStart();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mListener != null) {
            mListener.onRestart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (applyATE()) {
//            if (ATE.didValuesChange(this, this.updateTime, this.getATEKey())) {
//                this.recreate();
//            }
//        }
        if (mListener != null) {
            mListener.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mListener != null) {
            mListener.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mListener != null) {
            mListener.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mListener != null) {
            mListener.onDestroy();
        }
        //移除view绑定
        if (unBinder != null) {
            unBinder.unbind();
        }
    }

    public Toolbar initToolBar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
            toolbarTitle.setText(title);
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        return toolbar;
    }

    public Toolbar initToolBarAsHome(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            TextView toolbaTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
            toolbaTitle.setText(title);
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        return toolbar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                //返回
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * 回调函数
     */
    public LifecycleListener mListener;

    public void setOnLifeCycleListener(LifecycleListener listener) {
        mListener = listener;
    }

    /**
     * 统一退出控制
     */
    @Override
    public void onBackPressed() {
//        if (this instanceof MainActivity) {
//            /**
//             * 如果是主页面
//             */
//            if (System.currentTimeMillis() - mPreTime > UIUtils.getInteger(R.integer.exit_seconds)) {
//                // 两次点击间隔大于2秒
//                ToastUtils.showShort(R.string.two_back_exit);
//                mPreTime = System.currentTimeMillis();
//                return;
//            }
//            ActivityUtils.finishAllActivities();
//        }
        super.onBackPressed();
    }

    /**
     * 获取上一个界面传送过来的数据
     */

    protected void initBundleData() {
    }

    /**
     * 显示进度条
     */
    protected void showLoading() {
    }

    /**
     * 隐藏进度条
     */
    protected void hideLoading() {
    }
//
//    /*********************ATE相关***************************/
//    private long updateTime = -1L;
//
//    public BaseActivity() {
//    }
//
//    @Nullable
//    protected String getATEKey() {
//        return Helpers.getATEKey(this);
//    }
//
//    /**
//     * 是否使用改变主题相关工具
//     *
//     * @return
//     */
//    protected boolean applyATE() {
//        return false;
//    }
//
//    public boolean onCreateOptionsMenu(Menu menu) {
//        if (applyATE()) {
//            if (menu.size() > 0) {
//                ATE.applyMenu(this, this.getATEKey(), menu);
//            }
//        }
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        if (applyATE()) {
//            ATE.applyOverflow(this, this.getATEKey());
//        }
//        return super.onPrepareOptionsMenu(menu);
//    }
}
