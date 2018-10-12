package com.caramelheaven.lennach.presentation.main;

import android.annotation.SuppressLint;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.presentation.board.BoardFragment;
import com.caramelheaven.lennach.presentation.main.navigation.BottomNavigationDrawerFragment;
import com.caramelheaven.lennach.presentation.main.presenter.MainPresenter;
import com.caramelheaven.lennach.presentation.main.presenter.MainView;
import com.caramelheaven.lennach.utils.HideMainBottomBar;

import timber.log.Timber;

public class MainActivity extends MvpAppCompatActivity implements HideMainBottomBar, MainView {

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
