package com.caramelheaven.lennach.presentation.splash_welcome;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.presentation.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startActivity(new Intent(SplashActivity.this, MainActivity.class));
    }
}
