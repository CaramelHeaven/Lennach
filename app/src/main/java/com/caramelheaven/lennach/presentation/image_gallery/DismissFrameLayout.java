package com.caramelheaven.lennach.presentation.image_gallery;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import timber.log.Timber;

public class DismissFrameLayout extends FrameLayout {
    private SwipeGestureDetector swipeGestureDetector;
    private OnDismissListener dismissListener;

    private int initHeight; //child view's original height;
    private int initWidth;
    private int initLeft = 0;
    private int initTop = 0;

    private float oldDeltaFromTopY = 0;
    private float oldDeltaFromMiddleY = 0;

    public DismissFrameLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public DismissFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DismissFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(21)
    public DismissFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        swipeGestureDetector = new SwipeGestureDetector(getContext(),
                new SwipeGestureDetector.OnSwipeGestureListener() {
                    @Override
                    public void onSwipeTopBottom(float deltaX, float deltaY) {
                        Timber.d("onSwipeTopBottom deltaX: " + deltaX + " deltay: " + deltaY);
                        dragChildView(deltaX, deltaY);
                    }

                    @Override
                    public void onSwipeLeftRight(float deltaX, float deltaY) {
                        Timber.d("onSwipeLeftRight: " + deltaX + " dY: " + deltaY);
                    }

                    @Override
                    public void onFinish(int direction, float distanceX, float distanceY) {
                        Timber.d("onFinish: " + distanceX + " -: " + distanceY);
                        if (dismissListener != null
                                && direction == SwipeGestureDetector.DIRECTION_TOP_BOTTOM) {
                            if (distanceY > initHeight / 10 || distanceY < initHeight / 10) {
                                dismissListener.onDismiss();
                            } else {
                                dismissListener.onCancel();
                                reset();
                            }
                        }
                    }
                });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Timber.d("onInterceptTouchEvent: ");
        return swipeGestureDetector.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Timber.d("onTouchEvent");
        return swipeGestureDetector.onTouchEvent(event);
    }

    /**
     * Effect from entering
     *
     * @param deltaX
     * @param deltaY
     */
    private void dragChildView(float deltaX, float deltaY) {
        Timber.d("dragChildView");
        int count = getChildCount();
        if (count > 0) {
            //take image and manipulate it
            View view = getChildAt(0);
            scaleAndMove(view, deltaX, deltaY);
        }
    }

    /**
     * Min increase photo 1/2
     *
     * @param view
     * @param deltaY
     */
    private void scaleAndMove(View view, float deltaX, float deltaY) {
        MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();

        if (params == null) {
            params = new MarginLayoutParams(view.getWidth(), view.getHeight());
        }

        if (params.width <= 0 && params.height <= 0) {
            params.width = view.getWidth();
            params.height = view.getHeight();
        }

        //Called once
        if (initHeight <= 0) {
            initHeight = view.getHeight();
            initWidth = view.getWidth();
            initLeft = params.leftMargin;
            initTop = params.topMargin;
        }

        float percent = 0;

        if (deltaY <= 0) {
            percent = Math.abs(deltaY) / getHeight();
        } else if (initHeight > params.height && deltaY >= 0 && initHeight > view.getHeight()) {
            if (oldDeltaFromTopY == 0) {
                oldDeltaFromTopY = deltaY;
            } else {
                oldDeltaFromTopY -= deltaY;
            }
            oldDeltaFromTopY /= 1.6;

            percent = oldDeltaFromTopY / getHeight();
        } else {
            //TODO working with image increase/decrease
        }

        int scaleX = (int) (initWidth * percent);
        int scaleY = (int) (initHeight * percent);

        Timber.d("scalyX: " + scaleX + " scalyY: " + scaleY);

        params.width = params.width - scaleX;
        params.height = params.height - scaleY;

        Timber.d("scaleDown width: " + params.width + " - : " + params.height);
//        Log.d("scaleDown", params.width + "-" + params.height);
        params.leftMargin += (calXOffset(deltaX) + scaleX / 2);
        params.topMargin += (calYOffset(deltaY) + scaleY / 2);

        view.setLayoutParams(params);

        if (dismissListener != null) {
            dismissListener.onScaleProgress(percent);
        }
    }

    private int calXOffset(float deltaX) {
        return (int) deltaX;
    }

    private int calYOffset(float deltaY) {
        return (int) deltaY;
    }

    private void reset() {
        int count = getChildCount();
        if (count > 0) {
            View view = getChildAt(0);
            MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();

            params.width = initWidth;
            params.height = initHeight;
            params.leftMargin = initLeft;
            params.topMargin = initTop;

            view.setLayoutParams(params);
        }
    }

    public void setDismissListener(OnDismissListener dismissListener) {
        this.dismissListener = dismissListener;
    }

    public interface OnDismissListener {
        void onScaleProgress(float scale);

        void onDismiss();

        void onCancel();
    }
}