package com.caramelheaven.lennach.presentation.image_gallery;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.view.ViewPager;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.transition.Transition;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.common.DataImage;
import com.caramelheaven.lennach.presentation.base.BaseFragment;

import java.util.List;
import java.util.Map;

import timber.log.Timber;

public class ImageGalleryFragment extends MvpAppCompatFragment {

    private ImagePagerAdapter pagerAdapter;

    private ViewPager vpContainer;

    public static ImageGalleryFragment newInstance(DataImage image) {

        Bundle args = new Bundle();
        args.putParcelable("IMAGE", image);

        ImageGalleryFragment fragment = new ImageGalleryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_gallery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        vpContainer = view.findViewById(R.id.viewPager);

        Timber.d("on image gallery appear");

        DataImage image = getArguments().getParcelable("IMAGE");

        pagerAdapter = new ImagePagerAdapter(this, 3, image);
        vpContainer.setAdapter(pagerAdapter);

        vpContainer.setCurrentItem(0);

        vpContainer.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });

        prepareSharedElementTransition();

        if (savedInstanceState == null) {
            postponeEnterTransition();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        vpContainer = null;
    }

    private void prepareSharedElementTransition() {
        Transition transition = TransitionInflater.from(getActivity())
                .inflateTransition(R.transition.image_enter_shared_transition);
        setSharedElementEnterTransition(transition);

        setEnterSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                Fragment currentFragment = (Fragment) vpContainer.getAdapter()
                        .instantiateItem(vpContainer, 0);

                View view = currentFragment.getView();

                if (view == null) {
                    return;
                }

                sharedElements.put(names.get(0), view.findViewById(R.id.iv_image));
            }
        });
    }

}
