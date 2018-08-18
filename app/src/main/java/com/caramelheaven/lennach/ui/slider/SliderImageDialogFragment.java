package com.caramelheaven.lennach.ui.slider;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.datasource.database.entity.helpers.PostsHelper;
import com.caramelheaven.lennach.ui.slider.presenter.SliderImagePresenter;
import com.caramelheaven.lennach.ui.slider.presenter.SliderImageView;

import java.util.ArrayList;

import timber.log.Timber;

public class SliderImageDialogFragment extends MvpAppCompatDialogFragment implements SliderImageView {

    private ArrayList<PostsHelper> postsHelperList;
    private int selectedPos;
    private ImageViewPagerAdapter pagerAdapter;

    private ViewPager viewPager;
    private TextView tvCount;

    @InjectPresenter
    SliderImagePresenter presenter;

    public static SliderImageDialogFragment newInstance(int currentPosition, ArrayList<PostsHelper> container) {
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
        return inflater.inflate(R.layout.fragment_image_slider, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewPager = view.findViewById(R.id.vp_container);
        tvCount = view.findViewById(R.id.tv_count);

        postsHelperList = getArguments().getParcelableArrayList("IMAGES");
        selectedPos = getArguments().getInt("POS");
        provideViewPager();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    private void provideViewPager() {
        pagerAdapter = new ImageViewPagerAdapter(getActivity(), postsHelperList);
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
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
