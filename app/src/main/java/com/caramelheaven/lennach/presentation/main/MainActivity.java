package com.caramelheaven.lennach.presentation.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.presentation.board.BoardFragment;
import com.caramelheaven.lennach.presentation.main.presenter.MainPresenter;
import com.caramelheaven.lennach.presentation.main.presenter.MainView;
import com.caramelheaven.lennach.presentation.main.saved_history.HistoryFragment;
import com.caramelheaven.lennach.utils.libraries.bottom_navigation.BottomNavigationViewEx;

import timber.log.Timber;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    private BottomNavigationViewEx bottomNavigation;
    private RelativeLayout rlContainer;

    private BottomSheetBehavior bottomSheetBehavior;

    @InjectPresenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rlContainer = findViewById(R.id.bottom_sheet);
        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomSheetBehavior = BottomSheetBehavior.from(rlContainer);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        rlContainer.setVisibility(View.VISIBLE);

        provideBottomNavigation();

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int state) {
                Timber.d("onStateChanged: " + state);
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

    }

    private void provideBottomNavigation() {
        bottomNavigation.setTextVisibility(false);
        bottomNavigation.enableAnimation(false);
        bottomNavigation.enableShiftingMode(0, false);
        bottomNavigation.enableShiftingMode(1, false);
        bottomNavigation.enableShiftingMode(2, false);
        bottomNavigation.enableShiftingMode(3, false);

        bottomNavigation.setCurrentItem(0);

        bottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.item_base_page:
                    handlerHomePage();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, BoardFragment.newInstance("b"))
                            .commit();
                    return true;
                case R.id.item_saved_history:
                    handlerHistory();
                    return true;
                case R.id.item_create:
                    handlerItemCreate();
                    return true;
                case R.id.item_favourite:
                    handlerItemFavourite();
                    return true;
                case R.id.item_menu:
                    handlerItemMenu();
                    return true;
            }
            return false;
        });
    }

    private void handlerHomePage() {
        closeBottomExpandLayout();
    }

    private void handlerHistory() {
        if (!checkBottomExpandLayoutState()) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_navigation, HistoryFragment.newInstance())
                    .commit();
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }

    private void handlerItemCreate() {
        closeBottomExpandLayout();
    }

    private void handlerItemFavourite() {
        closeBottomExpandLayout();
    }

    private void handlerItemMenu() {
        closeBottomExpandLayout();
    }

    //if open return true
    private boolean checkBottomExpandLayoutState() {
        return bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED ||
                bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED;
    }

    private void closeBottomExpandLayout() {
        if (checkBottomExpandLayoutState()) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }
}
