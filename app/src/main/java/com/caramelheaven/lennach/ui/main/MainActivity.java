package com.caramelheaven.lennach.ui.main;

import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.ui.board.BoardFragment;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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
