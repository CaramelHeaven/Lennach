package com.caramelheaven.lennach.utils.views.item_touch_thread;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.caramelheaven.lennach.presentation.thread.ThreadAdapter;
import com.caramelheaven.lennach.utils.UtilsApplication;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 02:13, 04/01/2019.
 */
public class OnItemTouchHelperThread<T extends ThreadAdapter> extends ItemTouchHelper.Callback {

    private T adapter;
    private Context context;
    private boolean vibrate = false;

    public OnItemTouchHelperThread(T adapter, Context context) {
        this.adapter = adapter;
        this.context = context;
    }

    public void clear() {
        context = null;
        adapter = null;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, ItemTouchHelper.START);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ThreadAdapter.PostVH holder = (ThreadAdapter.PostVH) viewHolder;

        holder.itemView.animate()
                .translationX(0)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        setVibrate(false);
                    }
                })
                .start();

        adapter.notifyItemChanged(holder.getAdapterPosition());
        adapter.itemTouchCallback(holder.getAdapterPosition());
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        ThreadAdapter.PostVH holder = (ThreadAdapter.PostVH) viewHolder;
        Timber.d("check math abs: " + Math.abs(dX));
        if (Math.abs(dX) < 300) {
            holder.itemView.setTranslationX(dX);
        } else {
            if (!isVibrate()) {
                UtilsApplication.getInstance().makeVibration(context);
                setVibrate(true);
            }
        }
    }

    public boolean isVibrate() {
        return vibrate;
    }

    public void setVibrate(boolean vibrate) {
        this.vibrate = vibrate;
    }
}
