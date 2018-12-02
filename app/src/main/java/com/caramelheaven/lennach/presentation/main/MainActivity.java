package com.caramelheaven.lennach.presentation.main;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.presentation.board.BoardFragment;
import com.caramelheaven.lennach.presentation.image_gallery.ImageGalleryActivity;
import com.caramelheaven.lennach.presentation.main.presenter.MainPresenter;
import com.caramelheaven.lennach.presentation.main.presenter.MainView;
import com.caramelheaven.lennach.utils.bus.GlobalBus;
import com.caramelheaven.lennach.utils.bus.Kek;
import com.caramelheaven.lennach.utils.views.bottom_navigation.BottomNavigationViewEx;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import timber.log.Timber;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    /*
     * All data passing to gallery
     * */
    private View[] imageViews;

    private BottomSheetBehavior bottomSheetBehavior;
    private RelativeLayout relativeLayout;
    private BottomNavigationViewEx btnNavigation;
    private ProgressBar progressBar;

    @InjectPresenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(relativeLayout);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        btnNavigation = findViewById(R.id.bottom_navigation);
        progressBar = findViewById(R.id.progressBar);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, BoardFragment.newInstance())
                    .commit();
        }

        provideBottomNavigation();

        provideClickListeners();
    }

    @Override
    public void onResume() {
        super.onResume();
        GlobalBus.getEventBus().register(this);
    }

    @Override
    public void onPause() {
        GlobalBus.getEventBus().unregister(this);
        super.onPause();
        Timber.d("UNREGISTER");
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void authUpdate(Kek kek) {
        View view = kek.getView();
        Intent intent = new Intent(MainActivity.this, ImageGalleryActivity.class);

        intent.putParcelableArrayListExtra("IMAGES", kek.getDataImages());
        intent.putExtra("CURRENT_CLICKED_POS", kek.getPos());
        intent.putExtra("LIST_POSITION", kek.getCurrentPos());

        //set transition name
        view.setTransitionName(getBaseContext().getResources()
                .getString(R.string.transition_name, kek.getPos(),
                        kek.getCurrentPos()));

        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(
                MainActivity.this, view, ViewCompat.getTransitionName(view));

        startActivityForResult(intent, 0, activityOptions.toBundle());
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
//            presenter.setExitImageSwipePosition(
//                    data.getIntExtra("EXIT",
//                            presenter.getEnterImageClickPosition()
//                    ));
        }
    }

    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    private void provideBottomNavigation() {
        btnNavigation.setTextVisibility(false);
        btnNavigation.enableItemShiftingMode(false);
        btnNavigation.enableAnimation(false);
        btnNavigation.enableShiftingMode(false);

        btnNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            Timber.d("CLICK");
            switch (menuItem.getItemId()) {
                case R.id.item_navigation:
                    Timber.d("item navigation");
                    provideItemNavigation();
                    break;
                case R.id.item_start_page:
                    //  provideItemStartPage();
                    break;
                case R.id.item_notification:
                    //   provideItemNotification();
                    break;
                case R.id.item_menu:
                    //   provideItemMenu();
                    break;
            }
            return true;
        });
    }

    private void provideItemNavigation() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }
    //                .commit();
    //                .replace(R.id.fragment_container_main, MenuFragment.newInstance())
    //                .beginTransaction()
    //        getSupportFragmentManager()
    //    private void provideItemMenu() {
    //
    //    }
    //                .commit();
    //                .replace(R.id.fragment_container_main, NotificationFragment.newInstance())
    //                .beginTransaction()
    //        getSupportFragmentManager()
    //    private void provideItemNotification() {
    //
    //    }
    //                .commit();
    //                .replace(R.id.fragment_container_main, BoardFragment.newInstance())
    //                .beginTransaction()
    //        getSupportFragmentManager()
//    private void provideItemStartPage() {

//    }

    private void provideClickListeners() {

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                Timber.d("on changes: " + i);
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

    }
}

