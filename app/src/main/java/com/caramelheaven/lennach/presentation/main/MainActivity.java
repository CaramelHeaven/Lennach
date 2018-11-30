package com.caramelheaven.lennach.presentation.main;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.board.Usenet;
import com.caramelheaven.lennach.presentation.board.BoardFragment;
import com.caramelheaven.lennach.presentation.image_gallery.ImageGalleryActivity;
import com.caramelheaven.lennach.presentation.main.presenter.MainPresenter;
import com.caramelheaven.lennach.presentation.main.presenter.MainView;
import com.caramelheaven.lennach.presentation.menu.MenuFragment;
import com.caramelheaven.lennach.presentation.notification.NotificationFragment;
import com.caramelheaven.lennach.utils.OnBoardItemClickListener;
import com.caramelheaven.lennach.utils.bus.GlobalBus;
import com.caramelheaven.lennach.utils.view.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class MainActivity extends MvpAppCompatActivity implements MainView<Usenet> {

    private BoardAdapter adapter;

    /*
     * All data passing to gallery
     * */
    private View[] imageViews;

    private BottomSheetBehavior bottomSheetBehavior;
    private RelativeLayout relativeLayout;
    private BottomNavigationViewEx btnNavigation;
    private RecyclerView recyclerView;
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
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        provideBottomNavigation();

        provideRecyclerAndAdapter();
        provideClickListeners();

        //provideItemStartPage();


    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            presenter.setExitImageSwipePosition(
                    data.getIntExtra("EXIT",
                            presenter.getEnterImageClickPosition()
                    ));
        }
    }

    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showItems(List<Usenet> items) {
        Timber.d("items: " + items.size());
        if (items.size() != 0) {
            adapter.updateAdapter(items);
            runLayoutAnimation();
        }
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
        Timber.d("get state: " + bottomSheetBehavior.getState());
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

    private void provideRecyclerAndAdapter() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new BoardAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
    }

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

        adapter.setOnBoardItemClickListener(new OnBoardItemClickListener() {
            @Override
            public void onImageClick(int pos, View view) {
                Timber.d("pos: " + pos);
                Intent intent = new Intent(MainActivity.this, ImageGalleryActivity.class);

                intent.putParcelableArrayListExtra("IMAGES", adapter.getImages());
                intent.putExtra("CURRENT_CLICKED_POS", pos);
                intent.putExtra("LIST_POSITION", adapter.getPositionByItem(adapter.getItemByPosition(pos)));

                //Установка идентичного transition. У нас список элементов, где для каждого
                // будет свой пос
                view.setTransitionName(getBaseContext().getResources()
                        .getString(R.string.transition_name, pos,
                                adapter.getPositionByItem(adapter.getItemByPosition(pos))));

                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(
                        MainActivity.this, view, ViewCompat.getTransitionName(view));

                startActivityForResult(intent, 0, activityOptions.toBundle());
            }

            @Override
            public void onThreadClick(int pos) {

            }
        });

    }

    private void runLayoutAnimation() {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_from_bottom);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
}

