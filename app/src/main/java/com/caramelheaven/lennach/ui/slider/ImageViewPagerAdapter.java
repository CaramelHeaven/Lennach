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
import com.bumptech.glide.request.RequestOptions;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.datasource.database.entity.helpers.PostsHelper;
import com.caramelheaven.lennach.datasource.database.entity.iFile;

import java.util.ArrayList;

public class ImageViewPagerAdapter extends PagerAdapter {

    private FragmentActivity activity;
    private ArrayList<PostsHelper> postsHelpers;

    public ImageViewPagerAdapter(FragmentActivity activity, ArrayList<PostsHelper> postsHelpers) {
        this.activity = activity;
        this.postsHelpers = postsHelpers;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_image_fullscreen, container, false);
        ImageView ivFullscreen = view.findViewById(R.id.iv_fullscreen);

        iFile iFile = postsHelpers.get(position).iFileList.get(0);

        Glide.with(ivFullscreen.getContext())
                .load("https://2ch.hk" + iFile.getPath())
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(ivFullscreen);

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return postsHelpers.size();
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
