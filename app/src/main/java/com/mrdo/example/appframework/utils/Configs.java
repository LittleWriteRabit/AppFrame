package com.mrdo.example.appframework.utils;

import android.util.Log;

import com.blankj.utilcode.util.ScreenUtils;

/**
 * Created by dulijie on 2018/9/20.
 */
public class Configs {

    public static final String TAG = "【ScreenUtils】";

    public static void printScreenDs() {
        Log.e(TAG, "=getScreenWidth=>" + ScreenUtils.getScreenWidth());
        Log.e(TAG, "=getScreenHeight=>" + ScreenUtils.getScreenHeight());
        Log.e(TAG, "=getScreenDensity=>" + ScreenUtils.getScreenDensity());
        Log.e(TAG, "=getScreenDensityDpi=>" + ScreenUtils.getScreenDensityDpi());
        Log.e(TAG, "=isLandscape=>" + ScreenUtils.isLandscape());
        Log.e(TAG, "isPortrait==>" + ScreenUtils.isPortrait());
    }
}
