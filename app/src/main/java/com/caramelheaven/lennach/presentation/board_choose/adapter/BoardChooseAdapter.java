package com.caramelheaven.lennach.presentation.board_choose.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.caramelheaven.lennach.models.model.board.BoardAll;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;

import java.util.List;

/**
 * Created by CaramelHeaven on 21:51, 20/01/2019.
 */
public class BoardChooseAdapter extends RecyclerView.Adapter {

    private AdapterDelegatesManager<List<BoardAll>> adapterDelegatesManager;

    private List<BoardAll> items;

    public BoardChooseAdapter(LayoutInflater inflater, List<BoardAll> items) {
        this.items = items;

        adapterDelegatesManager = new AdapterDelegatesManager<>();

        adapterDelegatesManager
                .addDelegate(new BoardChooseDelegate(inflater));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return adapterDelegatesManager.onCreateViewHolder(viewGroup, i);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        adapterDelegatesManager.onBindViewHolder(items, i, viewHolder);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setData(List<BoardAll> newItems) {
        this.items = newItems;

        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return adapterDelegatesManager.getItemViewType(items, position);
    }
}
