package com.caramelheaven.lennach.presentation.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.presentation.board.BoardFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, BoardFragment.newInstance("b"))
                    .commit();
        }
    }
}
