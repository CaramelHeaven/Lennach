package com.caramelheaven.lennach.ui.slider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.datasource.database.entity.iFile;
import com.caramelheaven.lennach.datasource.model.File;
import com.caramelheaven.lennach.models.model.board_viewer.Usenet;

import java.util.ArrayList;

public class ImageViewPagerAdapter extends PagerAdapter {

    private FragmentActivity activity;
    private ArrayList<Usenet> iFiles;

    public ImageViewPagerAdapter(FragmentActivity activity, ArrayList<Usenet> iFiles) {
        this.activity = activity;
        this.iFiles = iFiles;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_image_fullscreen, container, false);
        ImageView ivFullscreen = view.findViewById(R.id.iv_fullscreen);

//        Glide.with(ivFullscreen.getContext())
//                .load("https://2ch.hk" + iFiles.get(position).getThumbnail())
//                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
//                .into(ivFullscreen);

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return iFiles.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
