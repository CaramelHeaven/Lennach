package com.caramelheaven.lennach.ui.welcome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.ui.welcome.container.adapter.WelcomePagerAdapter;

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager vpContainer;
    private WelcomePagerAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        vpContainer = findViewById(R.id.vp_container);
        adapter = new WelcomePagerAdapter(getSupportFragmentManager());
        vpContainer.setAdapter(adapter);
    }
}
