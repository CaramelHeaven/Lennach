package com.caramelheaven.lennach.presentation.image_viewer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.caramelheaven.lennach.R;

import timber.log.Timber;

public class ImageViewerDialogFragment extends MvpAppCompatDialogFragment {

    private RelativeLayout rlContainer;

    public static ImageViewerDialogFragment newInstance() {

        Bundle args = new Bundle();

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

    }

    private float saveStartedLocation = 0f;
    private float currentStateLocation = 0f;

    @Override
    public void onStart() {
        super.onStart();
        //getDialog().getWindow().setBackgroundDrawableResource(R.drawable.fragment_dialog_rounded);
        rlContainer.setBackgroundColor(Color.parseColor("#33000000"));
        getDialog().getWindow().setBackgroundDrawable(rlContainer.getBackground());
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;

        getDialog().getWindow().setLayout(width, height);
        getDialog().getWindow().getDecorView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Timber.d("pressed: " + event.getRawY());
                        saveStartedLocation = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Timber.d("test: " + event.getRawY());
                        if (event.getAction() != MotionEvent.TOOL_TYPE_FINGER) {
                            currentStateLocation = event.getRawY() - saveStartedLocation;
                            v.animate()
                                    .y(currentStateLocation)
                                    .setDuration(0)
                                    .start();
                        }
//                        getDialog().getWindow().getDecorView().animate()
//                                .x(event.getRawX() + dX)
//                                .y(event.getRawY() + dY)
//                                .setDuration(0)
//                                .start();
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
                    default:
                        return false;
                }
                return true;
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
