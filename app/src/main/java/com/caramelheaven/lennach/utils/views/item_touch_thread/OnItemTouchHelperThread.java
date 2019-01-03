package com.caramelheaven.lennach.utils.views.item_touch_thread;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.caramelheaven.lennach.presentation.thread.ThreadAdapter;

/**
 * Created by CaramelHeaven on 02:13, 04/01/2019.
 */
public class OnItemTouchHelperThread<T extends ThreadAdapter> extends ItemTouchHelper.Callback {

    private T adapter;

    public OnItemTouchHelperThread(T adapter) {
        this.adapter = adapter;
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
                .start();

        adapter.notifyItemChanged(holder.getAdapterPosition());
        adapter.itemTouchCallback(holder.getAdapterPosition());
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        ThreadAdapter.PostVH holder = (ThreadAdapter.PostVH) viewHolder;
        if (Math.abs(dX) < 300) {
            holder.itemView.setTranslationX(dX);
        }
    }
}
