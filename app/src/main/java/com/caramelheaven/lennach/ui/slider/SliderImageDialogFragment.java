package com.caramelheaven.lennach.ui.slider;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.datasource.database.entity.iFile;
import com.caramelheaven.lennach.datasource.model.File;
import com.caramelheaven.lennach.models.model.board_viewer.Usenet;
import com.caramelheaven.lennach.ui.slider.presenter.SliderImagePresenter;
import com.caramelheaven.lennach.ui.slider.presenter.SliderImageView;

import java.util.ArrayList;

import timber.log.Timber;

public class SliderImageDialogFragment extends MvpAppCompatDialogFragment implements SliderImageView {

    private ArrayList<Usenet> filesList;
    private int selectedPos;
    private ImageViewPagerAdapter pagerAdapter;

    private ViewPager viewPager;
    private TextView tvCount;

    @InjectPresenter
    SliderImagePresenter presenter;

    public static SliderImageDialogFragment newInstance(int currentPosition, ArrayList<Usenet> container) {
        Bundle args = new Bundle();
        args.putInt("POS", currentPosition);
        args.putParcelableArrayList("IMAGES", container);

        SliderImageDialogFragment fragment = new SliderImageDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_slider, container, false);

        GestureDetector gesture = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                Timber.d("onDown");
                return true;
            }

        });
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Timber.d("event!: " + event.toString());
                return gesture.onTouchEvent(event);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewPager = view.findViewById(R.id.vp_container);
        tvCount = view.findViewById(R.id.tv_count);
        filesList = getArguments().getParcelableArrayList("IMAGES");
        selectedPos = getArguments().getInt("POS");
        provideViewPager();
    }

    @Override
    public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

        getDialog().getWindow().setDimAmount(0.60f);
        super.onResume();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    private void provideViewPager() {
        pagerAdapter = new ImageViewPagerAdapter(getActivity(), filesList);
        viewPager.setAdapter(pagerAdapter);

        GestureDetector gesture = new GestureDetector(getActivity(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                Timber.d("onDown");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                Timber.d("onShowPress");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                Timber.d("onSigleTapUp");
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Timber.d("OnScroll" + " distX: " + distanceX + " y: " + distanceY);
                Timber.d("mot e1: " + e1 + " e2: " + e2);
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                Timber.d("onLongPress");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Timber.d("onFLing: " + " e1: " + e1 + " e2 " + e2 + "veloX: " + velocityX + " veY " + velocityY);
                return true;
            }
        });

        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);
            }
        });

    }

}
