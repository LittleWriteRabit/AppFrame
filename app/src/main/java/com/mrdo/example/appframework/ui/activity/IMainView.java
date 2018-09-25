package com.mrdo.example.appframework.ui.activity;

import com.mrdo.example.appframework.base.IBaseView;
import com.mrdo.example.appframework.data.bean.OtcHomeData;

/**
 * Created by dulijie on 2018/9/12.
 */
public interface IMainView extends IBaseView {

    void showToast(String msg);

    void onSuccess(OtcHomeData data);
}
