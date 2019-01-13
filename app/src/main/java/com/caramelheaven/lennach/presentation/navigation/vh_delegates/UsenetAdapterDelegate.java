package com.caramelheaven.lennach.presentation.navigation.vh_delegates;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.board.Usenet;
import com.caramelheaven.lennach.models.model.common.Delegatable;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;

import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 13:57, 13/01/2019.
 */
public class UsenetAdapterDelegate extends AdapterDelegate<List<Delegatable>> {

    private LayoutInflater inflater;

    public UsenetAdapterDelegate(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    protected boolean isForViewType(@NonNull List<Delegatable> items, int position) {
        return items.get(position) instanceof Usenet;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new UsenetVH(inflater.inflate(R.layout.item_usenet_nagivation, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull List<Delegatable> items, int position,
                                    @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {
        UsenetVH usenetVH = (UsenetVH) holder;
        Usenet usenet = (Usenet) items.get(position);
        Timber.d("i'm here");

        Timber.d("items usenetVH: " + usenet.toString());
    }

    static class UsenetVH extends RecyclerView.ViewHolder {

        public UsenetVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
