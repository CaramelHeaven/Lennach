package com.caramelheaven.lennach.ui.thread.helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import timber.log.Timber;

public class RecyclerTouchListener extends RecyclerView.SimpleOnItemTouchListener {

    private ThreadClickListener clickListener;
    private GestureDetector gestureDetector;

    public RecyclerTouchListener(Context context, RecyclerView rvContainer, ThreadClickListener clickListener) {
        this.clickListener = clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
                Timber.d("onLongPress");
                View child = rvContainer.findChildViewUnder(e.getX(), e.getY());
                if (child != null && clickListener != null) {
                    clickListener.onLongClick(child, rvContainer.getChildAdapterPosition(child));
                }
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                Timber.d("onDubleTapEvent");
                return true;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Timber.d("onDoubleTap");
                return true;
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                Timber.d("onSingleTapUp");
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        Timber.d("onInterceptTouchEvent: " + e.getAction());
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onClick(child, rv.getChildAdapterPosition(child));
        }
        return false;
    }
}
