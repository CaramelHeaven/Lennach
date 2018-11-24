package com.caramelheaven.lennach.presentation.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.view.View;
import android.widget.RelativeLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.presentation.main.fragment.MainFragment;
import com.caramelheaven.lennach.presentation.main.presenter.MainPresenter;
import com.caramelheaven.lennach.presentation.main.presenter.MainView;
import com.caramelheaven.lennach.presentation.navigation.NavigationFragment;
import com.caramelheaven.lennach.utils.bus.GlobalBus;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import timber.log.Timber;

public class MainActivity extends MvpAppCompatActivity implements MainView {
    private BottomSheetBehavior bottomSheetBehavior;
    private RelativeLayout relativeLayout;

    @InjectPresenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(relativeLayout);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, MainFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        GlobalBus.getEventBus().register(this);
    }

    @Override
    public void onStop() {
        GlobalBus.getEventBus().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNavigationViewEvent(String event) {
        if (event.equals("event")) {
            Timber.d("provide navigation");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_navigation, NavigationFragment.newInstance(), "HISTORY")
                    .addToBackStack(null)
                    .commit();
            relativeLayout.setZ(1);
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}

