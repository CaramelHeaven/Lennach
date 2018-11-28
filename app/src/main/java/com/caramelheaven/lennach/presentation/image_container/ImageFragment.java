package com.caramelheaven.lennach.presentation.image_container;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.bumptech.glide.Glide;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.common.DataImage;

import timber.log.Timber;

public class ImageFragment extends MvpAppCompatFragment {

    private ImageView ivImage;

    public static ImageFragment newInstance(int position, DataImage image) {

        Bundle args = new Bundle();
        args.putInt("POS", position);
        args.putParcelable("IMAGE", image);

        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Timber.d("iv image fragment appear");
        ivImage = view.findViewById(R.id.iv_image);

        ivImage.setTransitionName(getResources().getString(R.string.image_transition));

        DataImage image = getArguments().getParcelable("IMAGE");

        Timber.d("load picture: " + image.getThumbnail());

        Glide.with(this)
                .load("https://2ch.hk" + image.getThumbnail())
                .into(ivImage);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
