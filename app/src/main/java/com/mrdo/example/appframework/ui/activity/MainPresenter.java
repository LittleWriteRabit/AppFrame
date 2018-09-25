package com.mrdo.example.appframework.ui.activity;

import com.mrdo.example.appframework.base.BaseActivity;
import com.mrdo.example.appframework.base.BasePresenter;
import com.mrdo.example.appframework.base.BaseThemeActivity;
import com.mrdo.example.appframework.data.base.HttpResponse;
import com.mrdo.example.appframework.data.bean.OtcHomeData;
import com.mrdo.example.appframework.network.ApiCallback;
import com.mrdo.example.appframework.network.ApiClient;
import com.mrdo.example.appframework.network.ApiRxObservable;
import com.mrdo.example.appframework.network.ExceptionHandle;
import com.mrdo.example.appframework.network.ProgressUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by dulijie on 2018/9/12.
 */
public class MainPresenter extends BasePresenter<IMainView,BaseThemeActivity> {
    public static final String TAG=MainPresenter.class.getSimpleName();

    public MainPresenter(IMainView mView, BaseThemeActivity mActivity) {
        super(mView, mActivity);
    }

    public void loadTest(){
        ApiCallback<OtcHomeData> apiCallback=new ApiCallback<OtcHomeData>(TAG+"loadTest") {
            @Override
            protected void onStart(Disposable disposable) {

            }

            @Override
            protected void onError(ExceptionHandle.ResponseThrowable responseThrowable) {
                if(getView()!= null){
                    getView().showToast(responseThrowable.message);
                }
            }

            @Override
            protected void onSuccess(HttpResponse<OtcHomeData> stringHttpResponse) {
                if(getView()!= null){
                    getView().onSuccess(stringHttpResponse.data);
                }
            }
        };
        ApiRxObservable.getObservable(ApiClient.getInstance().getApiService().homedata(),mActivity)
                //根据是否需要显示进度条来进行修改
                .compose(ProgressUtils.<HttpResponse<OtcHomeData>>applyProgressBar(mActivity,true,"Loading..."))
                .subscribe(apiCallback);
    }
}
