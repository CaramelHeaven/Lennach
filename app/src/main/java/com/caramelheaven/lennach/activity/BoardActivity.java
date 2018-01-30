package com.caramelheaven.lennach.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.caramelheaven.lennach.fragments.BoardMoreFragment;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.fragments.ThreadFragment;

public class BoardActivity extends AppCompatActivity {

    private static final String LOGS = BoardActivity.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        int currentOrientation = getResources().getConfiguration().orientation;

        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            BoardMoreFragment boardFragment = new BoardMoreFragment();
            ThreadFragment threadFragment = new ThreadFragment();

            boardFragment.setArguments(getIntent().getExtras());
            threadFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.boardContainer, boardFragment)
                    .add(R.id.threadContainer, threadFragment)
                    .commit();
        } else {
            BoardMoreFragment boardFragment = new BoardMoreFragment();
            boardFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.boardContainer, boardFragment)
                    .commit();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
