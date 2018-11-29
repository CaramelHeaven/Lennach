package com.caramelheaven.lennach.presentation.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.view.View;
import android.widget.RelativeLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.presentation.board.BoardFragment;
import com.caramelheaven.lennach.presentation.main.presenter.MainPresenter;
import com.caramelheaven.lennach.presentation.main.presenter.MainView;
import com.caramelheaven.lennach.presentation.menu.MenuFragment;
import com.caramelheaven.lennach.presentation.notification.NotificationFragment;
import com.caramelheaven.lennach.utils.bus.GlobalBus;
import com.caramelheaven.lennach.utils.view.BottomNavigationViewEx;

import timber.log.Timber;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    private BottomSheetBehavior bottomSheetBehavior;
    private RelativeLayout relativeLayout;
    private BottomNavigationViewEx btnNavigation;

    @InjectPresenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(relativeLayout);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        btnNavigation = findViewById(R.id.bottom_navigation);

        provideBottomNavigation();

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                Timber.d("on changes: " + i);
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

        //provideItemStartPage();


    }

    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    private void provideBottomNavigation() {
        btnNavigation.setTextVisibility(false);
        btnNavigation.enableItemShiftingMode(false);
        btnNavigation.enableAnimation(false);
        btnNavigation.enableShiftingMode(false);

        btnNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            Timber.d("CLICK");
            switch (menuItem.getItemId()) {
                case R.id.item_navigation:
                    Timber.d("item navigation");
                    provideItemNavigation();
                    break;
                case R.id.item_start_page:
                    //  provideItemStartPage();
                    break;
                case R.id.item_notification:
                    //   provideItemNotification();
                    break;
                case R.id.item_menu:
                    //   provideItemMenu();
                    break;
            }
            return true;
        });
    }

    private void provideItemNavigation() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        Timber.d("get state: " + bottomSheetBehavior.getState());
    }

//    private void provideItemStartPage() {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fragment_container_main, BoardFragment.newInstance())
//                .commit();
//    }
//
//    private void provideItemNotification() {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fragment_container_main, NotificationFragment.newInstance())
//                .commit();
//    }
//
//    private void provideItemMenu() {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fragment_container_main, MenuFragment.newInstance())
//                .commit();
//    }
}

