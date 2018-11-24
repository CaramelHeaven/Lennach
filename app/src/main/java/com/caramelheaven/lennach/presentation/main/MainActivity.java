package com.caramelheaven.lennach.presentation.main;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.presentation.main.presenter.MainView;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
