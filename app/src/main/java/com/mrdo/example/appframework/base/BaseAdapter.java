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

/**
 * Created by dulijie on 2018/9/12.
 * RecyclerView BaseAdapter
 */
public abstract class BaseAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    public BaseAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public BaseAdapter(@Nullable List<T> data) {
        super(data);
    }

    public BaseAdapter(int layoutResId) {
        super(layoutResId);
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
        setEmptyView(view);
    }
}
