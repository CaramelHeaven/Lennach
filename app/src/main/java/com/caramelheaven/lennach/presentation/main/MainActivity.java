package com.caramelheaven.lennach.presentation.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.presentation.main.presenter.MainPresenter;
import com.caramelheaven.lennach.presentation.main.presenter.MainView;
import com.caramelheaven.lennach.utils.libraries.bottom_navigation.BottomNavigationViewEx;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    private BottomNavigationViewEx bottomNavigation;
    private RelativeLayout rlContainer;

    private BottomSheetBehavior bottomSheetBehavior;

    @InjectPresenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rlContainer = findViewById(R.id.bottom_sheet);
        bottomNavigation = findViewById(R.id.bottom_navigation);

        provideBottomNavigation();


//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragment_container_navigation, BottomNavigationFragment.newInstance())
//                .commit();
//
//        bottomSheetBehavior = BottomSheetBehavior.from(rlContainer);
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//        rlContainer.setVisibility(View.VISIBLE);
//
//        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View view, int state) {
//                Timber.d("onStateChanged: " + state);
//                if (BottomSheetBehavior.STATE_HIDDEN == state) {
//                    fabMenu.animate().scaleX(1).scaleY(1).setDuration(300).start();
//                }
//            }
//
//            @Override
//            public void onSlide(@NonNull View view, float v) {
//
//            }
//        });
//
//        if (savedInstanceState == null) {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .add(R.id.fragment_container, BoardFragment.newInstance("b"))
//                    .commit();
//        }

//
//        fabMenu.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("CheckResult")
//            @Override
//            public void onClick(View v) {
//                fabMenu.animate().scaleX(0).scaleY(0).setDuration(300).start();
//                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
////                Lennach.getLennach().getDatabase().getUsenetDao().getSavedUsenets()
////                        .subscribeOn(Schedulers.io())
////                        .observeOn(AndroidSchedulers.mainThread())
////                        .subscribe(result -> {
////                            Timber.d("result: " + result.toString());
////                        }, throwable -> {
////                            Timber.d("th: " + throwable.getCause());
////                            Timber.d("th: " + throwable.getMessage());
////                        });
//            }
//        });
//    }
    }

    private void provideBottomNavigation() {
        bottomNavigation.setTextVisibility(false);
        bottomNavigation.enableAnimation(true);
        bottomNavigation.enableShiftingMode(0, false);
        bottomNavigation.enableShiftingMode(1, false);
        bottomNavigation.enableShiftingMode(2, false);

        bottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.item_favorite:
                    Toast.makeText(MainActivity.this, "fav", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.item_menu:
                    Toast.makeText(MainActivity.this, "menu", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        });
    }
}
