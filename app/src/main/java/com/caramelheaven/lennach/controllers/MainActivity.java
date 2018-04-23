package com.caramelheaven.lennach.controllers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.controllers.board.BoardFragment;

public class MainActivity extends BaseActivity {

    private TextView textWelcome;
    private Toolbar toolbar;

    private static final String LOGS = MainActivity.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        displayHomeAsUpEnabled();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        textWelcome = findViewById(R.id.textWelcome);
        textWelcome.setVisibility(View.VISIBLE);
        Button button = findViewById(R.id.button);

        Toast.makeText(this, "asfaf", Toast.LENGTH_SHORT).show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textWelcome.setVisibility(View.GONE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, BoardFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });

    }
}


