package com.caramelheaven.lennach.controllers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.caramelheaven.lennach.R;

public class FullScreenImageFragment extends Fragment {

    private String fileUrl;
    private static final String LOGS = FullScreenImageFragment.class.getSimpleName();

    public static FullScreenImageFragment newInstance(String fileUrl) {

        Bundle args = new Bundle();
        args.putString("FILE_URL", fileUrl);
        FullScreenImageFragment fragment = new FullScreenImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_full_screen_image, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ImageView imageFull = view.findViewById(R.id.fullScreenImageView);
        fileUrl = getArguments().getString("FILE_URL");
        Log.d(LOGS, "file URL: " + fileUrl);
        Glide.with(getContext())
                .load("https://2ch.hk/" + fileUrl)
                .apply(RequestOptions.centerCropTransform())
                .into(imageFull);
    }
}
