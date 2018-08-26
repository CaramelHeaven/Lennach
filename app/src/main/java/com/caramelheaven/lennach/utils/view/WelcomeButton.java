package com.caramelheaven.lennach.utils.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.caramelheaven.lennach.R;

import timber.log.Timber;

public class WelcomeButton extends View {

    private Paint paint;
    private Animation showAnimation;
    private Animation hideAnimation;
    private boolean isClicked;

    public WelcomeButton(Context context) {
        super(context);
        init(null);
    }

    public WelcomeButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attributeSet) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        showAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.scale_show_item_welcome);
        hideAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.scale_hide_item_welcome);
        isClicked = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredWidth() / 2, getMeasuredWidth() / 2, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2); // set stroke width
        paint.setColor(Color.parseColor("#C9C9C9")); // set stroke color
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredWidth() / 2, getMeasuredWidth() / 2, paint);
    }

    public void setClicked(View view) {
        if (view.getVisibility() == View.GONE) {
            view.setVisibility(VISIBLE);
            view.startAnimation(showAnimation);
            isClicked = true;
        } else {
            view.startAnimation(hideAnimation);
            view.setVisibility(GONE);
            isClicked = false;
        }
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setRecovery(View view) {
        Timber.d("view: " + view);
        view.setVisibility(VISIBLE);
        Timber.d("YES");
        Timber.d("view: " + view.getVisibility());
        view.startAnimation(showAnimation);
    }
}
