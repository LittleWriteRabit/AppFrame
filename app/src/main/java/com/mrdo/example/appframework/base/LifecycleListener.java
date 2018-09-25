package com.mrdo.example.appframework.base;

import android.os.Bundle;

/**
 * Created by dulijie on 2018/9/10.
 */
public interface LifecycleListener {

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onRestart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();
}
