package com.caramelheaven.lennach.presentation.image_viewer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.TransitionInflater;
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
import com.caramelheaven.lennach.utils.HideImageViewer;
import com.caramelheaven.lennach.utils.HideMainBottomBar;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class ImageViewerFragment extends ParentFragment implements ImageViewerView {

    private RelativeLayout rlContainer;
    private ViewPager vpGallery;
    private HideImageViewer hideImageViewer;

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
        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_fullscreen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //  rlContainer = view.findViewById(R.id.rl_container);
        vpGallery = view.findViewById(R.id.vp_gallery);
        List<Usenet> usenets = getArguments().getParcelableArrayList("IMAGES");
        int position = getArguments().getInt("POS");
        Timber.d("posotion: " + position);
        imageViewPager = new ImageViewPager(getActivity(), usenets);
        vpGallery.setAdapter(imageViewPager);

        vpGallery.setCurrentItem(position);
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

        imageViewPager.setImageViewerCallback(flag -> {
            presenter.closeGallery(true);
            getActivity()
                    .getSupportFragmentManager()
                    .popBackStack();
        });
    }

    @Override
    public void onStart() {
        super.onStart();

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
}
