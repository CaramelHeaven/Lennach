package com.caramelheaven.lennach.utils;

import android.support.v7.util.DiffUtil;

import com.caramelheaven.lennach.models.model.board.Board;
import com.caramelheaven.lennach.models.model.board.Usenet;
import com.caramelheaven.lennach.models.model.common.Delegatable;

import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 15:00, 13/01/2019.
 * Not working
 */
public class UtilsDiffCallbackNavigation extends DiffUtil.Callback {

    private List<Delegatable> oldItems;
    private List<Delegatable> newItems;

    public UtilsDiffCallbackNavigation(List<Delegatable> oldItems, List<Delegatable> newItems) {
        this.oldItems = oldItems;
        this.newItems = newItems;
    }

    @Override
    public int getOldListSize() {
        return oldItems.size();
    }

    @Override
    public int getNewListSize() {
        return newItems.size();
    }

    @Override
    public boolean areItemsTheSame(int i, int i1) {
        boolean kek = false;
        if (oldItems.get(i) instanceof Board && newItems.get(i1) instanceof Board) {
            return ((Board) oldItems.get(i)).getBoardName().equals(((Board) newItems.get(i1)).getBoardName());
        } else if (oldItems.get(i) instanceof Usenet && newItems.get(i1) instanceof Usenet) {
            kek = ((Usenet) oldItems.get(i)).getNum().equals(((Usenet) newItems.get(i1)).getNum());
        }
        Timber.d("kek areItemsTheSame: " + kek);

        return kek;
    }

    @Override
    public boolean areContentsTheSame(int i, int i1) {
        boolean kek = oldItems.get(i).equals(newItems.get(i1));
        Timber.d("contains the same: " + kek);

        return kek;
    }
}
