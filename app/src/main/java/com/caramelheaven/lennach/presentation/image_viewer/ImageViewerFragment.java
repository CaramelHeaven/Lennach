package com.caramelheaven.lennach.presentation.image_viewer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.board_viewer.Usenet;
import com.caramelheaven.lennach.presentation.base.ParentFragment;
import com.caramelheaven.lennach.presentation.image_viewer.presenter.ImageViewerPresenter;
import com.caramelheaven.lennach.presentation.image_viewer.presenter.ImageViewerView;
import com.caramelheaven.lennach.utils.Constants;
import com.caramelheaven.lennach.utils.callbacks.BottomBarHandler;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class ImageViewerFragment extends ParentFragment implements ImageViewerView {

    private RelativeLayout rlContainer;
    private ViewPager vpGallery;

    private ImageViewPager imageViewPager;

    @InjectPresenter
    ImageViewerPresenter presenter;

    public static ImageViewerFragment newInstance(int position, ArrayList<Usenet> usenetList) {

        Bundle args = new Bundle();
        args.putInt("POS", position);
        args.putParcelableArrayList("IMAGES", usenetList);
        ImageViewerFragment fragment = new ImageViewerFragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_fullscreen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rlContainer = view.findViewById(R.id.rl_container);
        vpGallery = view.findViewById(R.id.vp_gallery);
        List<Usenet> usenets = getArguments().getParcelableArrayList("IMAGES");
        int position = getArguments().getInt("POS");
        imageViewPager = new ImageViewPager(getActivity(), usenets);
        vpGallery.setAdapter(imageViewPager);

        rlContainer.getBackground().setAlpha(255);
        vpGallery.setCurrentItem(position);
        provideViewPager();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected void initRecyclerAndAdapter() {

    }

    private void provideViewPager() {
        vpGallery.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        imageViewPager.setImageViewerCallback(new ImageViewerCallback() {
            @Override
            public void close(boolean flag) {
                getActivity()
                        .getSupportFragmentManager()
                        .popBackStack();
            }

            @Override
            public void passAlphaCounter(float data) {
                if (data == Constants.BLACK_BACKGROUND) {
                    rlContainer.getBackground().setAlpha(255);
                } else {
                    rlContainer.getBackground().setAlpha(255 - (Math.round(data) / 2));
                }
            }
        });
    }
}
