package com.caramelheaven.lennach.presentation.image_gallery;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.caramelheaven.lennach.models.model.common.DataImage;
import com.caramelheaven.lennach.presentation.image_container.ImageFragment;

public class ImagePagerAdapter extends FragmentStatePagerAdapter {

    private int length = 0;
    private DataImage image;

    public ImagePagerAdapter(Fragment fragment, int length, DataImage image) {
        super(fragment.getChildFragmentManager());
        this.length = length;
        this.image = image;
    }

    @Override
    public Fragment getItem(int i) {
        return ImageFragment.newInstance(i, image);
    }

    @Override
    public int getCount() {
        return length;
    }
}
