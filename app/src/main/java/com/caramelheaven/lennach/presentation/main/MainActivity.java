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

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.presentation.board.BoardFragment;
import com.caramelheaven.lennach.presentation.main.navigation.BottomNavigationDrawerFragment;
import com.caramelheaven.lennach.utils.HideMainBottomBar;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements HideMainBottomBar {

    private BottomAppBar bottomAppBar;
    private FloatingActionButton fabCreateThread;

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

    @SuppressLint("RestrictedApi")
    @Override
    public void hide(boolean flag) {
        Timber.d("callback main: " + flag);
        if (flag) {
            bottomAppBar.setVisibility(View.GONE);
            fabCreateThread.setVisibility(View.GONE);
        } else {
            bottomAppBar.setVisibility(View.VISIBLE);
            fabCreateThread.setVisibility(View.VISIBLE);
        }
    }
}
