package com.caramelheaven.lennach.utils;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 01:19, 04/01/2019.
 */
public class UtilsView {

    private static UtilsView INSTANCE;

    public static UtilsView getInstance() {
        if (INSTANCE == null) {
            synchronized (UtilsView.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UtilsView();
                }
            }
        }

        return INSTANCE;
    }

    public static void expand(final View v, int duration, int targetHeight) {
        int prevHeight = v.getHeight();
        v.setVisibility(View.VISIBLE);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(prevHeight, targetHeight);
        valueAnimator.addUpdateListener(animation -> {
            v.getLayoutParams().height = (int) animation.getAnimatedValue();
            v.requestLayout();
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(duration);

        valueAnimator.start();
    }

    public static void collapse(final View v, int duration, int targetHeight) {
        int prevHeight = v.getHeight();
        ValueAnimator valueAnimator = ValueAnimator.ofInt(prevHeight, targetHeight);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(animation -> {
            v.getLayoutParams().height = (int) animation.getAnimatedValue();
            v.requestLayout();
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(duration);
        valueAnimator.start();
    }

    public static int dpToPx(int dp, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        int kek = Math.round((float) dp * density);
        Timber.d("kek: " + kek);

        return kek;
    }
}
