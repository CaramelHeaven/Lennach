package com.caramelheaven.lennach.presentation.image_gallery;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.common.DataImage;
import com.caramelheaven.lennach.presentation.image_gallery.presenter.ImageGalleryPresenter;
import com.caramelheaven.lennach.presentation.image_gallery.presenter.ImageGalleryView;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class ImageGalleryActivity extends MvpAppCompatActivity implements ImageGalleryView {

    private List<DataImage> dataImages;
    private PhotoAdapter adapter;
    private ColorDrawable colorDrawable;
    private int ALPHA_MAX = 0xFF;

    private ViewPager viewPager;
    private FrameLayout container;

    @InjectPresenter
    ImageGalleryPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postponeEnterTransition();
        setContentView(R.layout.activity_image_gallery);

        viewPager = findViewById(R.id.viewPager);

        //coloring background
        container = findViewById(R.id.frameContainer);
        colorDrawable = new ColorDrawable(getResources().getColor(R.color.colofDarkFrameLayout));
        container.setBackgroundDrawable(colorDrawable);

        dataImages = new ArrayList<>(getIntent().getParcelableArrayListExtra("IMAGES"));
        presenter.setCurrentClickedUserPosition(getIntent().getIntExtra("CURRENT_CLICKED_POS", 0));
        presenter.setListPosition(getIntent().getIntExtra("LIST_POSITION", 0));

        adapter = new PhotoAdapter(dataImages, onDismissListener);
        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(presenter.getListPosition());

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }


    private DismissFrameLayout.OnDismissListener onDismissListener = new DismissFrameLayout.OnDismissListener() {
        @Override
        public void onScaleProgress(float scale) {
            colorDrawable.setAlpha(
                    Math.min(ALPHA_MAX, colorDrawable.getAlpha() - (int) (scale * ALPHA_MAX)));
        }

        @Override
        public void onDismiss() {
            finishAfterTransition();
        }

        @Override
        public void onCancel() {
            colorDrawable.setAlpha(ALPHA_MAX);
        }
    };

    public class PhotoAdapter extends PagerAdapter {

        private List<DataImage> dataImages;
        DismissFrameLayout.OnDismissListener onDismissListener;

        PhotoAdapter(List<DataImage> dataImages, DismissFrameLayout.OnDismissListener onDismissListener) {
            this.dataImages = dataImages;
            this.onDismissListener = onDismissListener;
        }

        @Override
        public int getCount() {
            return dataImages.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView ivImage = new ImageView(container.getContext());

            ivImage.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ivImage.setScaleType(ImageView.ScaleType.FIT_CENTER);

            //what???
            String name = container.getContext()
                    .getString(R.string.transition_name, presenter.getCurrentClickedUserPosition(),
                            position);

            ViewCompat.setTransitionName(ivImage, name);

            Glide.with(ivImage)
                    .load("https://2ch.hk" + dataImages.get(position).getPath())
                    .apply(new RequestOptions()
                            .dontAnimate())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            startPostponedEnterTransition();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            Timber.d("resource ready");
                            startPostponedEnterTransition();
                            return false;
                        }
                    })
                    .into(ivImage);


            DismissFrameLayout layout = new DismissFrameLayout(container.getContext());

            layout.setDismissListener(onDismissListener);
            layout.setLayoutParams(new ViewPager.LayoutParams());
            layout.addView(ivImage);

            container.addView(layout);
            return layout;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }

}
