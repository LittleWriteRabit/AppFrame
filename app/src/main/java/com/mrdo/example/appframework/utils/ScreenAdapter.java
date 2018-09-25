package com.mrdo.example.appframework.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;

import com.mrdo.example.appframework.R;

/**
 * Created by dulijie on 2018/9/12.
 * 今日头条适配方案
 */
public class ScreenAdapter {

    private static float sNonCompatDensity;
    private static float sNonCompatScaleDensity;

    public static void setCustomDensity(@NonNull Activity activity, @NonNull final Application application) {
        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();

        if (sNonCompatDensity == 0) {
            sNonCompatDensity = appDisplayMetrics.density;
            sNonCompatScaleDensity = appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sNonCompatScaleDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        /**
         * 按那个比例适配就用那个比例替换掉  360dp
         * 1dp = ?px
         * 1dp=3px
         * 1080px/3=360dp
         */
//        final float targetDensity = appDisplayMetrics.widthPixels / 360;
        final float targetDensity = appDisplayMetrics.widthPixels / getScreenOriation(activity);
        final float targetScaleDensity = targetDensity * (sNonCompatScaleDensity / sNonCompatDensity);
        final int targetDensityDpi = (int) (160 * targetDensity);
        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.scaledDensity = targetScaleDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;

        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaleDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }

    /**
     * 获取屏幕宽高适配 按照切图做适配，通过 {@link }
     *
     * @param activity
     * @return
     */
    private static int getScreenOriation(@NonNull Activity activity) {
        Configuration mConfiguration = activity.getResources().getConfiguration(); //获取设置的配置信息
        int ori = mConfiguration.orientation; //获取屏幕方向
        if (ori == mConfiguration.ORIENTATION_LANDSCAPE) {
            //横屏
            return UIUtils.getInteger(R.integer.screen_height);
        } else/* if (ori == mConfiguration.ORIENTATION_PORTRAIT) */ {
            //竖屏
            return UIUtils.getInteger(R.integer.screen_width);
        }
    }
}
