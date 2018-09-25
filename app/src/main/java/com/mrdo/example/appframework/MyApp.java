package com.mrdo.example.appframework;

import android.content.Context;

import com.afollestad.appthemeengine.Config;
import com.blankj.utilcode.util.Utils;
import com.mrdo.example.appframework.base.BaseApp;
import com.mrdo.example.appframework.utils.Helpers;
import com.mrdo.example.appframework.utils.LogUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
//import com.squareup.leakcanary.LeakCanary;

/**
 * Created by dulijie on 2018/9/10.
 * Application
 */
public class MyApp extends BaseApp {

    static {
//        //设置全局的Header构建器
//        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
//            @Override
//            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
//                //全局设置主题颜色
//                String mAte = Helpers.getATEKey(context);
//                if (mAte != null) {
//                    layout.setPrimaryColors(Config.primaryColor(context, mAte),
//                            Config.accentColor(context,mAte));
//                } else {
//                    layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
//                }
//                //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
//                return new ClassicsHeader(context);
//            }
//        });
//        //设置全局的Footer构建器
//        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
//            @Override
//            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
//                //指定为经典Footer，默认是 BallPulseFooter
//                return new ClassicsFooter(context).setDrawableSize(20);
//            }
//        });
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            //全局设置主题颜色
            String mAte = Helpers.getATEKey(context);
            if (mAte != null) {
                layout.setPrimaryColors(Config.primaryColor(context, mAte),
                        Config.accentColor(context,mAte));
            } else {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            }
            //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            return new ClassicsHeader(context);
        });

        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            return new ClassicsFooter(context).setDrawableSize(20);
        });

    }

    public static MyApp sApp;

    @Override
    public void onCreate() {
        super.onCreate();
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
        // Normal app init code...
        sApp = this;
        Utils.init(this);
        LogUtil.init();

    }

}
