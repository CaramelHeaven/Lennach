package com.caramelheaven.lennach.utils.item_touch;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.caramelheaven.lennach.ui.thread.ThreadAdapter;
import com.loopeer.itemtouchhelperextension.ItemTouchHelperExtension;

import java.security.Key;

import timber.log.Timber;

public class ItemTouchHelperCallback extends ItemTouchHelperExtension.Callback {
    private ThreadAdapter adapter;

    public ItemTouchHelperCallback(ThreadAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, ItemTouchHelper.START);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Timber.d("onSwiped");
        //super.onSwiped(viewHolder, direction);
        ThreadAdapter.PostVH holder = (ThreadAdapter.PostVH) viewHolder;
//        super.onChildDraw(canvas, recyclerView, holder, 1080, 0, ItemTouchHelper.END, true);
        //    holder.itemView.setTranslationX(600);
        holder.itemView.animate()
                .translationX(0)
                .start();
        adapter.notifyItemChanged(holder.getAdapterPosition());
    }

    RecyclerView recyclerView;
    Canvas canvas;

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        ThreadAdapter.PostVH holder = (ThreadAdapter.PostVH) viewHolder;
        Timber.d("dx: " + dX);
        Timber.d("checking: " + holder.itemView.getWidth());
        Timber.d("acionState: " + actionState + " isCurrentlyActive: " + isCurrentlyActive);

        if (Math.abs(dX) < 700) {
            holder.itemView.setTranslationX(dX);
            //super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }
}
