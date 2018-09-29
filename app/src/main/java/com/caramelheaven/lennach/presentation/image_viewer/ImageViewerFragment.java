package com.caramelheaven.lennach.presentation.image_viewer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.presentation.base.ParentFragment;

import timber.log.Timber;

public class ImageViewerFragment extends ParentFragment {

    private ImageView ivThread;

    public static ImageViewerFragment newInstance() {

        Bundle args = new Bundle();

        ImageViewerFragment fragment = new ImageViewerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_fullscreen, container, false);

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
                return gesture.onTouchEvent(event);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ivThread = view.findViewById(R.id.iv_picture_thread);
    }

    @Override
    protected void initRecyclerAndAdapter() {

    }

}
