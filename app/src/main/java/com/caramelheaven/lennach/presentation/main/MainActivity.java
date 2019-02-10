package com.caramelheaven.lennach.presentation.main;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.board.Board;
import com.caramelheaven.lennach.presentation.image_gallery.ImageGalleryActivity;
import com.caramelheaven.lennach.presentation.main.presenter.MainPresenter;
import com.caramelheaven.lennach.presentation.main.presenter.MainView;
import com.caramelheaven.lennach.presentation.navigation.NavigationFragment;
import com.caramelheaven.lennach.utils.bus.GlobalBus;
import com.caramelheaven.lennach.utils.bus.models.HandlerViewPagerData;
import com.caramelheaven.lennach.utils.bus.models.Kek;
import com.caramelheaven.lennach.utils.views.BottomNavigationViewEx;
import com.caramelheaven.lennach.utils.views.SlidePageTransformer;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import timber.log.Timber;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    /*
     * All data passing to gallery
     * */
    private View[] imageViews;
    private MainPagerAdapter adapter;
    private SlidePageTransformer slidePageTransformer;

    private BottomSheetBehavior bottomSheetBehavior;
    private RelativeLayout relativeLayout;
    private BottomNavigationViewEx btnNavigation;
    private ProgressBar progressBar;
    private ViewPager viewPager;

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
        viewPager = findViewById(R.id.viewPager);

        adapter = new MainPagerAdapter(getSupportFragmentManager());
        slidePageTransformer = new SlidePageTransformer();

        viewPager.setPageTransformer(false, slidePageTransformer);
        viewPager.setAdapter(adapter);

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
    public void increasePhotoPicker(Kek kek) {
        View view = kek.getView();
        Intent intent = new Intent(MainActivity.this, ImageGalleryActivity.class);

        intent.putParcelableArrayListExtra("IMAGES", kek.getDataImages());
        intent.putExtra("CURRENT_CLICKED_POS", kek.getPos());
        intent.putExtra("LIST_POSITION", kek.getCurrentPos());

        //set transition name
        ViewCompat.setTransitionName(view, getBaseContext().getResources()
                .getString(R.string.transition_name, kek.getPos(),
                        kek.getCurrentPos()));

        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(
                MainActivity.this, view, ViewCompat.getTransitionName(view));

        startActivityForResult(intent, 0, activityOptions.toBundle());
    }

    /**
     * When user click on thread item, we pass it here and pass again to the ThreadFragment GlobalBus
     * listener, after that, we set view pager to 1
     *
     * @param data - HandlerViewPagerData container which contains number of thread and current board
     *             where user located
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handlerPager(HandlerViewPagerData data) {
        GlobalBus.getEventBus().post(data.getActionThread());
        viewPager.setCurrentItem(1);
    }

    /**
     * Handler callback from BoardItemAdapter. Set the BoardFragment a new board
     *
     * @param board - board data where we should reflect in the BoardFragment
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handlerCallbackFromFavouriteScreenOpenBoard(Board board) {

    }

    /**
     * Handler callback when user want to add new board. Show a new DialogFragment where contains
     * all boards name from server
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handlerCallbackFromFavouriteScreenAddNewBoard(String addNewBoard) {

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
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_navigation, NavigationFragment.newInstance())
                .commit();

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {

            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });
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

    @Override
    public void hideBottomNavigation() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }
}

