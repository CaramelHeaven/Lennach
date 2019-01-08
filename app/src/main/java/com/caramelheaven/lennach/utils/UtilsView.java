package com.caramelheaven.lennach.utils;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.caramelheaven.lennach.utils.interfaces.ReplyMessages;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 01:19, 04/01/2019.
 */
public class UtilsView implements ReplyMessages {

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

    @Override
    public void expandFromNullToReply(View view, Context context) {
        expand(view, 300, dpToPx(140, context));
    }

    @Override
    public void expandFromReplyToAll(View view, int screenHeight, Context context) {
        expand(view, 300, dpToPx(screenHeight, context));
    }

    @Override
    public void collapseFromAllToReply(View view, Context context) {
        collapse(view, 300, dpToPx(140, context));
    }

    @Override
    public void collapseFromReplyToNull(View view, Context context) {
        collapse(view, 300, dpToPx(0, context));
    }

    private static void expand(final View v, int duration, int targetHeight) {
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

    private static void collapse(final View v, int duration, int targetHeight) {
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

    private static int dpToPx(int dp, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        int kek = Math.round((float) dp * density);

        return kek;
    }
}
