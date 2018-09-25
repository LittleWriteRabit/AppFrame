package com.mrdo.example.appframework.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mrdo.example.appframework.R;

import java.util.List;

public abstract class BaseRetryAdapter<M, N extends BaseViewHolder> extends BaseQuickAdapter<M, N> {

    public BaseRetryAdapter(int layoutResId) {
        super(layoutResId);
    }

    public BaseRetryAdapter(int layoutResId, @Nullable List<M> data) {
        super(layoutResId, data);
    }

    public BaseRetryAdapter(@Nullable List<M> data) {
        super(data);
    }

    @Override
    public void bindToRecyclerView(RecyclerView recyclerView) {
        super.bindToRecyclerView(recyclerView);
    }

    public void showEmptyView() {
        super.setEmptyView(R.layout.empty_layout);
    }

    public void showLoadView() {
        setEmptyView(R.layout.loading_layout);
    }

    public void showRetryView(View.OnClickListener clickListener) {
        Context context = getRecyclerView().getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.erro_layout, getRecyclerView(), false);
        view.findViewById(R.id.retry_button).setOnClickListener(clickListener);
        view.findViewById(R.id.error_image).setOnClickListener(clickListener);
        view.findViewById(R.id.error_text).setOnClickListener(clickListener);
        setEmptyView(view);
    }
}
