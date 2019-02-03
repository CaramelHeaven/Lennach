package com.caramelheaven.lennach.presentation.base;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.arellomobile.mvp.MvpAppCompatActivity;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 14:35, 03/02/2019.
 */
public abstract class BaseActivity extends MvpAppCompatActivity {

    /**
     * Method of initial views
     */
    protected abstract void initViews();

    protected abstract void attachClassesToViews();

    /**
     * Method of init listeners or buses etc.
     */
    protected abstract void initListeners();

    /**
     * Method of remove all callbacks, listeners, buses when activity destroy
     */
    protected abstract void removeHandlers();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        initViews();
        attachClassesToViews();
        initListeners();
    }

    @Override
    protected void onDestroy() {
        removeHandlers();
        super.onDestroy();
    }
}
