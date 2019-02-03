package com.caramelheaven.lennach.utils.animations;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.caramelheaven.lennach.utils.animations.util_helpers.Collapsible;
import com.caramelheaven.lennach.utils.animations.util_helpers.Expandable;

import org.jetbrains.annotations.NotNull;

/**
 * Created by CaramelHeaven on 23:34, 03/02/2019.
 */
public class UtilsAnimationView implements Expandable, Collapsible {
    private static volatile UtilsAnimationView INSTANCE;

    public static UtilsAnimationView getInstance() {
        if (INSTANCE == null) {
            synchronized (UtilsAnimationView.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UtilsAnimationView();
                }
            }
        }

        return INSTANCE;
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

    @Override
    public void expandFromZeroToHalfOfPartScreen(@NotNull View view, @NotNull Context context) {
        expand(view, 300, dpToPx(140, context));
    }

    @Override
    public void expandFromHalfOfPartToAllScreen(@NotNull View view, int screenHeight, @NotNull Context context) {
        expand(view, 300, dpToPx(screenHeight, context));
    }

    @Override
    public void collapseFromAllScreenHalfOfPart(@NotNull View view, @NotNull Context context) {
        collapse(view, 300, dpToPx(140, context));
    }

    @Override
    public void collapseFromHalfOfPartToZero(@NotNull View view, @NotNull Context context) {
        collapse(view, 300, dpToPx(0, context));
    }
}
