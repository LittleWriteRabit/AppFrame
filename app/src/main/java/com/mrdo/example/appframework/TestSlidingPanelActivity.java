package com.mrdo.example.appframework;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.mrdo.example.appframework.base.BaseActivity;
import com.mrdo.example.appframework.base.BasePresenter;
import com.mrdo.example.appframework.widget.slidinguppanel.SlidingUpPanelLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dulijie on 2018/9/19.
 */
public class TestSlidingPanelActivity extends BaseActivity {
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.panel_layout)
    FrameLayout panelLayout;
    @BindView(R.id.sliding_layout)
    SlidingUpPanelLayout slidingLayout;
    @BindView(R.id.topContainer)
    View topContainer;

    @Override
    protected void init() {
        setPanelSlideListeners(slidingLayout);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.layout_test_slidingpanel;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    public void setPanelSlideListeners(SlidingUpPanelLayout panelLayout) {
        panelLayout.setPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {

            @Override
            public void onPanelSlide(View panel, float slideOffset) {
//                View nowPlayingCard = QuickControlsFragment.topContainer;
//                nowPlayingCard.setAlpha(1 - slideOffset);
                topContainer.setAlpha(1 - slideOffset);
            }

            @Override
            public void onPanelCollapsed(View panel) {
//                View nowPlayingCard = QuickControlsFragment.topContainer;
//                nowPlayingCard.setAlpha(1);
                topContainer.setAlpha(1);
            }

            @Override
            public void onPanelExpanded(View panel) {
//                View nowPlayingCard = QuickControlsFragment.topContainer;
                topContainer.setAlpha(0);
            }

            @Override
            public void onPanelAnchored(View panel) {

            }

            @Override
            public void onPanelHidden(View panel) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if(slidingLayout.isPanelExpanded()){
            slidingLayout.collapsePanel();
        }else {
            super.onBackPressed();
        }
    }
}
