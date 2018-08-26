package com.caramelheaven.lennach.ui.slider;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
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
import com.caramelheaven.lennach.ui.slider.presenter.SliderImagePresenter;
import com.caramelheaven.lennach.ui.slider.presenter.SliderImageView;

import java.util.ArrayList;

import timber.log.Timber;

public class SliderImageDialogFragment extends MvpAppCompatDialogFragment implements SliderImageView {

    private ArrayList<iFile> filesList;
    private int selectedPos;
    private ImageViewPagerAdapter pagerAdapter;

    private ViewPager viewPager;
    private TextView tvCount;

    @InjectPresenter
    SliderImagePresenter presenter;

    public static SliderImageDialogFragment newInstance(int currentPosition, ArrayList<iFile> container) {
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

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                tvCount.setText(String.valueOf(i) + " of " + String.valueOf(filesList.size()));
                Timber.d("onPageScrolled");
            }

            @Override
            public void onPageSelected(int i) {
                Timber.d("OnPageSelected");
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }



}
