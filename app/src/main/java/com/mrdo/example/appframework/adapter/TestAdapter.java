package com.mrdo.example.appframework.adapter;

import android.support.annotation.Nullable;

import com.afollestad.appthemeengine.Config;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mrdo.example.appframework.base.BaseAdapter;
import com.mrdo.example.appframework.data.bean.CountrylistBean;
import com.mrdo.example.appframework.utils.Helpers;

import java.util.List;

/**
 * Created by dulijie on 2018/9/12.
 */
public class TestAdapter extends BaseAdapter<CountrylistBean> {

    public TestAdapter(@Nullable List<CountrylistBean> data) {
        super(android.R.layout.simple_list_item_1, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CountrylistBean item) {
        helper.setText(android.R.id.text1,item.countryName+"("+item.countryCode+")");
        helper.setTextColor(android.R.id.text1, /*Config.textColorPrimary(mContext, Helpers.getATEKey(mContext))*/
                Config.accentColor(mContext, Helpers.getATEKey(mContext)) );
    }
}
