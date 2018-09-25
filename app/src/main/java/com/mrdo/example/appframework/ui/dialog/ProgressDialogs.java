package com.mrdo.example.appframework.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.mrdo.example.appframework.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author dulijie  2018/9/11 17:20
 * @email dulijie@gichain.net
 * @description:TODO 加载进度条 菊花
 */
public class ProgressDialogs extends Dialog {

    Unbinder unbinder;
    @BindView(R.id.tv_progress)
    TextView tvTips;

    public ProgressDialogs(@NonNull Context context) {
        super(context, R.style.progress_dialog_no_frame);
        Window window = getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.progress_dialog);
        unbinder = ButterKnife.bind(this, this);
    }

    public void setText(String text) {
        if (tvTips != null) {
            if (!TextUtils.isEmpty(text)) {
                tvTips.setText(text);
                tvTips.setVisibility(View.VISIBLE);
            } else {
                tvTips.setVisibility(View.GONE);
            }
        }
    }

    public void setText(CharSequence text) {
        if (tvTips != null) {
            if (!TextUtils.isEmpty(text)) {
                tvTips.setText(text);
                tvTips.setVisibility(View.VISIBLE);
            } else {
                tvTips.setVisibility(View.GONE);
            }
        }
    }

    /**
     * @param resId string resId
     */
    public void setText(@StringRes int resId) {
        if (tvTips != null) {
            tvTips.setText(resId);
            tvTips.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}