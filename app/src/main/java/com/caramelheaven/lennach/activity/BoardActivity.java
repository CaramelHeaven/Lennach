package com.caramelheaven.lennach.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.caramelheaven.lennach.BoardFragment;
import com.caramelheaven.lennach.R;

public class BoardActivity extends AppCompatActivity {

    private static final String LOGS = BoardActivity.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        if (savedInstanceState == null) {
            BoardFragment boardFragment = new BoardFragment();

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.flContainer, boardFragment);
            ft.commit();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
