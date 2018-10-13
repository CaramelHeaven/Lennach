package com.caramelheaven.lennach.presentation.main;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.caramelheaven.lennach.Lennach;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.presentation.board.BoardFragment;
import com.caramelheaven.lennach.presentation.main.navigation.BottomNavigationDrawerFragment;
import com.caramelheaven.lennach.presentation.main.presenter.MainPresenter;
import com.caramelheaven.lennach.presentation.main.presenter.MainView;
import com.caramelheaven.lennach.utils.callbacks.BottomBarHandler;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends MvpAppCompatActivity implements BottomBarHandler, MainView {

    private BottomAppBar bottomAppBar;
    private FloatingActionButton fabCreateThread;

    @InjectPresenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomAppBar = findViewById(R.id.bottom_app_bar);
        fabCreateThread = findViewById(R.id.fab_create_thread);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, BoardFragment.newInstance("b"))
                    .commit();
        }

        bottomAppBar.setNavigationOnClickListener(v -> {
            BottomNavigationDrawerFragment bottomNav = BottomNavigationDrawerFragment.newInstance();
            bottomNav.show(getSupportFragmentManager(), null);
        });


        fabCreateThread.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {
                Lennach.getLennach().getDatabase().getUsenetDao().getSavedUsenets()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(result -> {
                            Timber.d("result: " + result.toString());
                        }, throwable -> {
                            Timber.d("th: " + throwable.getCause());
                            Timber.d("th: " + throwable.getMessage());
                        });
            }
        });
    }

    @Override
    public void hide(boolean flag) {
        presenter.menuBehavior(flag);
    }

    @Override
    public void transformToUsenet(boolean flag) {
        if (flag) {
            fabCreateThread.animate()
                    .alpha(0)
                    .setDuration(300)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            bottomAppBar.setNavigationIcon(null);
                            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
                            bottomAppBar.replaceMenu(R.menu.bottom_menu_secondary);
                            fabCreateThread.setImageDrawable(getDrawable(R.drawable.ic_history));
                            fabCreateThread.animate()
                                    .alpha(1)
                                    .setDuration(300)
                                    .start();
                        }
                    })
                    .start();
        } else {
            Timber.d("OLOLO");
            fabCreateThread.animate()
                    .alpha(0)
                    .setDuration(300)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                            bottomAppBar.replaceMenu(R.menu.bottom_menu_primary);
                            fabCreateThread.setImageDrawable(getDrawable(R.drawable.ic_create));
                            fabCreateThread.animate()
                                    .alpha(1)
                                    .setDuration(300)
                                    .start();
                        }
                    })
                    .start();
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void menuBehavior(boolean flag) {
        Timber.d("FLAG: " + flag);
        if (flag) {
            bottomAppBar.setVisibility(View.GONE);
            fabCreateThread.setVisibility(View.GONE);
        } else {
            bottomAppBar.setVisibility(View.VISIBLE);
            fabCreateThread.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void scrollBehavior(String data) {
        switch (data) {
            case "HIDE":
                if (presenter.isAllowToHide()) {
                    presenter.setAllowToHide(false);
                    bottomAppBar.animate()
                            .translationY(bottomAppBar.getHeight())
                            .alpha(0)
                            .setDuration(300)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    presenter.setAllowToHide(true);
                                    bottomAppBar.setVisibility(View.GONE);
                                }
                            })
                            .start();
                    fabCreateThread.animate()
                            .translationY(-fabCreateThread.getHeight() / 2 + 20)
                            .setDuration(300)
                            .start();
                }
                break;
            case "SHOW":
                if (presenter.isAllowToShow()) {
                    bottomAppBar.setVisibility(View.VISIBLE);
                    presenter.setAllowToShow(false);
                    bottomAppBar.animate()
                            .translationY(0)
                            .alpha(1)
                            .setDuration(300)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    presenter.setAllowToShow(true);
                                }
                            })
                            .start();
                    fabCreateThread.animate()
                            .translationY(-fabCreateThread.getHeight() / 2)
                            .setDuration(300)
                            .start();
                }
                break;
            default:
                Timber.d("lalala");
        }
    }
}
