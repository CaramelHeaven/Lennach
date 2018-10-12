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
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.presentation.board.BoardFragment;
import com.caramelheaven.lennach.presentation.main.navigation.BottomNavigationDrawerFragment;
import com.caramelheaven.lennach.presentation.main.presenter.MainPresenter;
import com.caramelheaven.lennach.presentation.main.presenter.MainView;
import com.caramelheaven.lennach.utils.callbacks.BottomBarHandler;

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
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void hide(boolean flag) {
        Timber.d("callback main: " + flag);
        presenter.menuBehavior(flag);
    }

    @Override
    public void transformToUsenet(boolean flag) {
        if (flag) {
            bottomAppBar.setNavigationIcon(null);
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
            bottomAppBar.replaceMenu(R.menu.bottom_menu_secondary);
            fabCreateThread.setImageDrawable(getDrawable(R.drawable.ic_history));
        } else {
            fabCreateThread.hide();
        }
    }

    private boolean allowToHide = true, allowToShow = true;

    @Override
    public void scrollBehavior(String data) {
        Timber.d("DATA: " + data);
        switch (data) {
            case "HIDE":
                if (allowToHide) {
                    allowToHide = false;
                    bottomAppBar.animate()
                            .translationY(bottomAppBar.getHeight())
                            .alpha(0)
                            .setDuration(300)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    allowToHide = true;
                                }
                            })
                            .start();
                }
                break;
            case "SHOW":
                if (allowToShow) {
                    allowToShow = false;
                    bottomAppBar.animate()
                            .translationY(0)
                            .alpha(1)
                            .setDuration(300)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    allowToShow = true;
                                }
                            })
                            .start();
                }
                break;
            default:
                Timber.d("lalala");
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void menuBehavior(boolean flag) {
        if (flag) {
            bottomAppBar.setVisibility(View.GONE);
            fabCreateThread.setVisibility(View.GONE);
        } else {
            bottomAppBar.setVisibility(View.VISIBLE);
            fabCreateThread.setVisibility(View.VISIBLE);
        }
    }
}
