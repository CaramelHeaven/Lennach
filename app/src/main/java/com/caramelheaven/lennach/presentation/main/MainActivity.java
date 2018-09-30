package com.caramelheaven.lennach.presentation.main;

import android.support.design.bottomappbar.BottomAppBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.presentation.board.BoardFragment;
import com.caramelheaven.lennach.presentation.main.navigation.BottomNavigationDrawerFragment;

public class MainActivity extends AppCompatActivity {

    private BottomAppBar bottomAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomAppBar = findViewById(R.id.bottom_app_bar);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, BoardFragment.newInstance("b"))
                    .commit();
        }

        bottomAppBar.setNavigationOnClickListener(v -> {
            BottomNavigationDrawerFragment bottomNav = BottomNavigationDrawerFragment.newInstance();
            bottomNav.show(getSupportFragmentManager(), null);
        });
    }
}
