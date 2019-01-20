package com.caramelheaven.lennach.presentation.board_choose.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.board.BoardAll;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;

import java.util.List;

/**
 * Created by CaramelHeaven on 21:52, 20/01/2019.
 */
public class BoardChooseDelegate extends AdapterDelegate<List<BoardAll>> {

    private LayoutInflater inflater;

    public BoardChooseDelegate(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    protected boolean isForViewType(@NonNull List<BoardAll> items, int position) {
        return items.get(position) != null;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new BoardAllVH(inflater.inflate(R.layout.item_board_choose, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull List<BoardAll> items, int position, @NonNull RecyclerView.ViewHolder holder,
                                    @NonNull List<Object> payloads) {

    }

    static class BoardAllVH extends RecyclerView.ViewHolder {

        public BoardAllVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
