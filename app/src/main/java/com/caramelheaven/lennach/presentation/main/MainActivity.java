package com.caramelheaven.lennach.presentation.main;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.presentation.base.BaseActivity;
import com.caramelheaven.lennach.presentation.main.adapter.MainPagerAdapter;
import com.caramelheaven.lennach.presentation.main.presenter.MainPresenter;
import com.caramelheaven.lennach.presentation.main.presenter.MainView;
import com.caramelheaven.lennach.utils.views.BottomNavigationViewEx;
import com.caramelheaven.lennach.utils.views.SlidePageTransformer;

public class MainActivity extends BaseActivity implements MainView {

    private BottomNavigationViewEx bottomNavigation;
    private ViewPager viewPager;

    private MainPagerAdapter mainPagerAdapter;
    private SlidePageTransformer slidePageTransformer;

    @InjectPresenter
    MainPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initViews() {
        bottomNavigation = findViewById(R.id.bottom_navigation);
        viewPager = findViewById(R.id.viewPager);

        bottomNavigation.setTextVisibility(false);
        bottomNavigation.enableItemShiftingMode(false);
        bottomNavigation.enableAnimation(false);
        bottomNavigation.enableShiftingMode(false);
    }

    @Override
    protected void attachClassesToViews() {
        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        slidePageTransformer = new SlidePageTransformer();

        viewPager.setPageTransformer(false, slidePageTransformer);
        viewPager.setAdapter(mainPagerAdapter);
    }

    @Override
    protected void initListeners() {
        bottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.item_database:
                    return true;
                case R.id.item_start_page:
                    return true;
                case R.id.item_notification:
                    return true;
                case R.id.item_menu:
                    return true;
            }
            return false;
        });
    }

    @Override
    protected void removeHandlers() {

    }

    @Override
    public void showData() {

    }
}
