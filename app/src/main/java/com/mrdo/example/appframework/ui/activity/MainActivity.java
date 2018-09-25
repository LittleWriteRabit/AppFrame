package com.mrdo.example.appframework.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.appthemeengine.ATE;
import com.afollestad.appthemeengine.Config;
import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mrdo.example.appframework.R;
import com.mrdo.example.appframework.TestActivity;
import com.mrdo.example.appframework.adapter.TestAdapter;
import com.mrdo.example.appframework.base.BaseThemeActivity;
import com.mrdo.example.appframework.data.bean.CountrylistBean;
import com.mrdo.example.appframework.data.bean.OtcHomeData;
import com.mrdo.example.appframework.utils.Configs;
import com.mrdo.example.appframework.utils.glide.GlideUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseThemeActivity<MainPresenter> implements IMainView, OnRefreshListener, ColorChooserDialog.ColorCallback, BaseQuickAdapter.OnItemClickListener/*, ATEActivityThemeCustomizer*/ {

    @BindView(R.id.iv_glide)
    ImageView iv_glide;
    @BindView(R.id.iv_icon)
    ImageView iv_icon;
//    @BindView(R.id.tv_content)
//    TextView tv_content;
    @BindView(R.id.list_view)
    RecyclerView listView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.namess)
    TextView namess;
    @BindView(R.id.pwdss)
    TextView pwdss;

    TestAdapter testAdapter;

    /**
     * 修改主题色强调色相关
     */
    private String mAteKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("LeakCarry=>>", "onCreate");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                //将侧边栏顶部延伸至status bar
                mDrawerLayout.setFitsSystemWindows(true);
                //将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
                mDrawerLayout.setClipToPadding(false);
            }
        }
        setupNavigationIcons(navigationView);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(final MenuItem menuItem) {
                        updatePosition(menuItem);
                        return true;

                    }
                });
        mAteKey = getATEKey();
        namess.setTextColor(Config.accentColor(this, mAteKey));
        pwdss.setTextColor(Config.accentColor(this, mAteKey));
//        tv_content.setTextColor(Config.accentColor(this, mAteKey));
        Configs.printScreenDs();
    }

    @Override
    protected void init() {
        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536584654910&di=94e562e64f7d7c056736191faa947d2f&imgtype=0&src=http%3A%2F%2Fpic36.photophoto.cn%2F20150805%2F0017029562574888_b.jpg";
        GlideUtils.setImage(this, iv_icon, url);
        GlideUtils.setImageGrayscale(this, iv_glide, url);
//        tv_content.setMovementMethod(ScrollingMovementMethod.getInstance());
        listView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(mContext, R.drawable.item_divider_bkg));
        listView.addItemDecoration(itemDecoration);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setDisableContentWhenLoading(true);
        refreshLayout.setDisableContentWhenRefresh(true);
        refreshLayout.setOnRefreshListener(this);
        testAdapter = new TestAdapter(new ArrayList<CountrylistBean>());
        testAdapter.setOnItemClickListener(this);
        testAdapter.bindToRecyclerView(listView);
        refreshLayout.autoRefresh();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this, this);
//        return null;
    }

    @Override
    public void showToast(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.showShort(msg);
        }
        refreshLayout.finishRefresh();
    }

    @Override
    public void onSuccess(OtcHomeData data) {
//        if (tv_content != null && data != null) {
//            tv_content.setText(data.toString() + "");
//        }
        if (data != null) {
            testAdapter.setNewData(data.countrys);
        }
        refreshLayout.finishRefresh();
//        refreshLayout.finishLoadMore();
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        mvpPresenter.loadTest();
    }

    @Override
    protected boolean transStatusBar() {
        return false;
    }

    private void setupNavigationIcons(NavigationView navigationView) {

        //material-icon-lib currently doesn't work with navigationview of design support library 22.2.0+
        //set icons manually for now
        //https://github.com/code-mc/material-icon-lib/issues/15

        navigationView.getMenu().findItem(R.id.nav_library).setIcon(R.drawable.library_music);
        navigationView.getMenu().findItem(R.id.nav_playlists).setIcon(R.drawable.playlist_play);
        navigationView.getMenu().findItem(R.id.nav_queue).setIcon(R.drawable.music_note);
        navigationView.getMenu().findItem(R.id.nav_folders).setIcon(R.drawable.ic_folder_open_black_24dp);
        navigationView.getMenu().findItem(R.id.nav_nowplaying).setIcon(R.drawable.bookmark_music);
        navigationView.getMenu().findItem(R.id.nav_settings).setIcon(R.drawable.settings);
        navigationView.getMenu().findItem(R.id.nav_about).setIcon(R.drawable.information);

    }

    private void updatePosition(final MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_library:
                new ColorChooserDialog.Builder(this, R.string.primary_color)
                        .preselect(Config.primaryColor(this, mAteKey))
                        .show();
                break;
            case R.id.nav_playlists:
                new ColorChooserDialog.Builder(this, R.string.accent_color)
                        .preselect(Config.accentColor(this, mAteKey))
                        .show();
                break;
            case R.id.nav_folders:
                ToastUtils.showShort("folders");
                recreate();
                break;
            case R.id.nav_nowplaying:
                ToastUtils.showShort("nowplaying");
                break;
            case R.id.nav_queue:
                ToastUtils.showShort("queue");
                break;
            case R.id.nav_settings:
                ToastUtils.showShort("setting");
                break;
            case R.id.nav_about:
                mDrawerLayout.closeDrawers();
                ToastUtils.showShort("about");
                break;
            case R.id.nav_donate:
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                break;
        }
        menuItem.setChecked(true);
        mDrawerLayout.closeDrawers();
    }

    @Override
    public void onColorSelection(@NonNull ColorChooserDialog dialog, int selectedColor) {
        final Config config = ATE.config(this, getATEKey());
        switch (dialog.getTitle()) {
            case R.string.primary_color:
                config.primaryColor(selectedColor);
                break;
            case R.string.accent_color:
                config.accentColor(selectedColor);
                break;
        }
        config.commit();
        recreate(); // recreation needed to reach the checkboxes in the preferences layout
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ActivityUtils.startActivity(TestActivity.class);
    }
}