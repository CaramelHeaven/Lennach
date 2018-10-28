package com.caramelheaven.lennach.presentation.main;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.presentation.board.BoardFragment;
import com.caramelheaven.lennach.presentation.main.navigation.BottomNavigationFragment;
import com.caramelheaven.lennach.presentation.main.presenter.MainPresenter;
import com.caramelheaven.lennach.presentation.main.presenter.MainView;
import com.caramelheaven.lennach.utils.Constants;
import com.caramelheaven.lennach.utils.callbacks.BottomBarHandler;
import com.caramelheaven.lennach.utils.channel.SomeData;

import timber.log.Timber;

public class MainActivity extends MvpAppCompatActivity implements BottomBarHandler, MainView {

    private BottomAppBar bottomAppBar;
    private FloatingActionButton fabMenu;
    private RelativeLayout rlContainer;

    private BottomSheetBehavior bottomSheetBehavior;

    @InjectPresenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomAppBar = findViewById(R.id.bottom_app_bar);
        fabMenu = findViewById(R.id.fab_create_thread);
        rlContainer = findViewById(R.id.bottom_sheet);

        bottomAppBar.replaceMenu(R.menu.bottom_menu_primary);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_navigation, BottomNavigationFragment.newInstance())
                .commit();

        bottomSheetBehavior = BottomSheetBehavior.from(rlContainer);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        rlContainer.setVisibility(View.VISIBLE);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int state) {
                Timber.d("onStateChanged: " + state);
                if (BottomSheetBehavior.STATE_HIDDEN == state) {
                    fabMenu.animate().scaleX(1).scaleY(1).setDuration(300).start();
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, BoardFragment.newInstance("b"))
                    .commit();
        }


        bottomAppBar.setNavigationOnClickListener(v -> {

//            BottomNavigationDrawerFragment bottomNav = BottomNavigationDrawerFragment.newInstance();
//            bottomNav.show(getSupportFragmentManager(), null);
        });

        fabMenu.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {
                fabMenu.animate().scaleX(0).scaleY(0).setDuration(300).start();
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
//                Lennach.getLennach().getDatabase().getUsenetDao().getSavedUsenets()
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(result -> {
//                            Timber.d("result: " + result.toString());
//                        }, throwable -> {
//                            Timber.d("th: " + throwable.getCause());
//                            Timber.d("th: " + throwable.getMessage());
//                        });
            }
        });
    }

    @Override
    public void interactionBottomVisibility(SomeData data) {
        Timber.d("checking data: " + data.getData());
        if (data.getData() == Constants.HIDE_BOTTOM_BAR) {
            presenter.menuBehavior(true);
        } else {
            presenter.menuBehavior(false);
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void menuBehavior(boolean flag) {
        Timber.d("FLAG: " + flag);
        if (flag) {
            bottomAppBar.setVisibility(View.GONE);
            fabMenu.setVisibility(View.GONE);
        } else {
            bottomAppBar.setVisibility(View.VISIBLE);
            fabMenu.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void menuTransform(boolean flag) {
        Timber.d("flag: " + flag);
        if (flag) {
            fabMenu.animate().alpha(0).setDuration(200)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            fabMenu.animate()
                                    .alpha(1)
                                    .setDuration(200)
                                    .start();
                            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
                            bottomAppBar.replaceMenu(R.menu.bottom_menu_secondary);
                            fabMenu.setImageDrawable(getResources().getDrawable(R.drawable.ic_create));
                        }
                    })
                    .start();
        } else if (!flag) {
            fabMenu.animate().alpha(0).setDuration(250)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            fabMenu.animate()
                                    .alpha(1)
                                    .setDuration(300)
                                    .start();
                            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                            bottomAppBar.replaceMenu(R.menu.bottom_menu_primary);
                            fabMenu.setImageDrawable(getResources().getDrawable(R.drawable.ic_apps));
                        }
                    })
                    .start();
        }
    }

    @Override
    public void menuBehaviorScroll(int state) {
        switch (state) {
            case Constants.SMOOTH_HIDE_BOTTOM_APP_BAR:
                bottomAppBar.animate()
                        .translationY(bottomAppBar.getHeight())
                        .alpha(0)
                        .setDuration(200)
                        .start();
                break;
            case Constants.SMOOTH_SHOW_BOTTOM_APP_BAR:
                bottomAppBar.animate()
                        .translationY(0)
                        .alpha(1)
                        .setDuration(200)
                        .start();
                break;
        }

    }

    @Override
    public void scrollBehavior(String data) {
        Timber.d("data: " + data);
//        switch (data) {

//                break;
//            case "SHOW":
//                if (presenter.isAllowToShow()) {
//                    bottomAppBar.setVisibility(View.VISIBLE);
//                    presenter.setAllowToShow(false);
//                }
//                break;
//            default:
//                Timber.d("lalala");
//        }
    }
}
