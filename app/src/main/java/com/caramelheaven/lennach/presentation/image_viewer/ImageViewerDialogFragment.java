package com.caramelheaven.lennach.presentation.image_viewer;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.bumptech.glide.Glide;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.board_viewer.Usenet;
import com.caramelheaven.lennach.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class ImageViewerDialogFragment extends MvpAppCompatDialogFragment {

    private RelativeLayout rlContainer;
    private ViewPager vpGallery;

    private ImageViewPager imageViewPager;

    public static ImageViewerDialogFragment newInstance(ArrayList<Usenet> usenetList) {

        Bundle args = new Bundle();
        args.putParcelableArrayList("IMAGES", usenetList);
        ImageViewerDialogFragment fragment = new ImageViewerDialogFragment();

        fragment.setArguments(args);
        return fragment;
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

        imageViewPager = new ImageViewPager(getActivity(), usenets);
        vpGallery.setAdapter(imageViewPager);

        vpGallery.setCurrentItem(0);
        int kek = ViewPager.SCROLL_STATE_DRAGGING;
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

    }

    private float saveStartedLocation = 0f;
    private float currentStateLocation = 0f;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null) {
            Timber.d("dialog");
            getDialog().getWindow().setBackgroundDrawable(rlContainer.getBackground());
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            getDialog().getWindow().setLayout(width, height);
            rlContainer.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            Timber.d("pressed: " + event.getRawY());
                            saveStartedLocation = event.getRawY();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            Timber.d("test: " + event.getRawY() + " getRawX: " + event.getRawX());
                            if (event.getRawX() > 500f) {
                                Timber.d("event > 500f");
                                vpGallery.setCurrentItem(1, true);
                            } else {
                                if (event.getAction() != MotionEvent.TOOL_TYPE_FINGER) {
                                    currentStateLocation = event.getRawY() - saveStartedLocation;
                                    v.animate()
                                            .y(currentStateLocation)
                                            .setDuration(0)
                                            .start();
                                }
                            }
                            break;
                        case MotionEvent.TOOL_TYPE_FINGER:
                            Timber.d("finger: " + event.getRawY() + " dx " + event.getRawX());
                            if (currentStateLocation > 250f) {
                                dismiss();
                            } else {
                                Timber.d("entered");
                                v.animate()
                                        .y(0f)
                                        .setDuration(200)
                                        .start();
                            }
                            Timber.d("simple: " + event.getX() + " y: " + event.getY());
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            Timber.d("Action Cancel: " + event.getX() + " y: " + event.getY());
                            break;
                        case MotionEvent.ACTION_SCROLL:
                            Timber.d("Action SCROLL: " + event.getX() + " y: " + event.getY());
                            break;
                        case MotionEvent.AXIS_SCROLL:
                            Timber.d("Action AXIS SCROLL: " + event.getX() + " y: " + event.getY());
                            break;
                        default:
                            return false;
                    }
                    return true;
                }
            });
        } else {
            Timber.d("dialog is null");
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        if (getDialog() != null) {
            Timber.d("dialog");
        } else {
            Timber.d("dialog is null");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
