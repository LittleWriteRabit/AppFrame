package com.mrdo.example.appframework.utils;

import com.mrdo.example.appframework.BuildConfig;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Created by yzg on 2018/1/20.
 */

public class LogUtil {
    /**
     * 初始化log工具，在app入口处调用
     *
     */
    public static void init() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  //是否选择显示线程信息，默认为true
                .methodCount(0)         //方法数显示多少行，默认2行
                .methodOffset(7)        //隐藏方法内部调用到偏移量，默认5
//                .logStrategy(customLog) //打印日志的策略，默认LogCat
                .tag("LogInfo")   //自定义TAG全部标签，默认PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy){
            @Override public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    public static void d(String message) {
        Logger.d(message);
    }

    public static void i(String message) {
        Logger.i(message);
    }

    public static void w(String message, Throwable e) {
        String info = e != null ? e.toString() : "null";
        Logger.w(message + "：" + info);
    }

    public static void e(String message, Throwable e) {
        Logger.e(e, message);
    }

    public static void json(String json) {
        Logger.json(json);
    }
}

