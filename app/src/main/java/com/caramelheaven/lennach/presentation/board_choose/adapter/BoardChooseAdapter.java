package com.caramelheaven.lennach.presentation.board_choose.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.caramelheaven.lennach.models.model.board.BoardFavourite;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaramelHeaven on 21:51, 20/01/2019.
 */
public class BoardChooseAdapter extends RecyclerView.Adapter {

    private AdapterDelegatesManager<List<BoardFavourite>> adapterDelegatesManager;

    private List<BoardFavourite> items;

    public BoardChooseAdapter(LayoutInflater inflater, List<BoardFavourite> items) {
        this.items = items;

        adapterDelegatesManager = new AdapterDelegatesManager<>();

        BoardChooseDelegate delegate = new BoardChooseDelegate(inflater);
        adapterDelegatesManager
                .addDelegate(delegate);

        delegate.setOnCheckItemListener((position, isCheck) ->
                items.get(position).setSelected(isCheck));
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

    public List<BoardFavourite> getItems() {
        return items;
    }

    public List<BoardFavourite> filterItems() {
        List<BoardFavourite> filterList = new ArrayList<>();

        for (BoardFavourite boardAll : items) {
            if (boardAll.isSelected()) {
                filterList.add(boardAll);
            }
        }

        return filterList;
    }

    public void setData(List<BoardFavourite> newItems) {
        items.clear();
        items.addAll(newItems);

        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return adapterDelegatesManager.getItemViewType(items, position);
    }
}
