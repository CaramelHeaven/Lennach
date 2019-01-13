package com.caramelheaven.lennach.presentation.navigation.vh_delegates;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.transfer_data.BoardContainer;
import com.caramelheaven.lennach.models.model.common.Delegatable;
import com.caramelheaven.lennach.presentation.navigation.BoardItemAdapter;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 13:57, 13/01/2019.
 */
public class BoardContainerAdapterDelegate extends AdapterDelegate<List<Delegatable>> {

    private LayoutInflater inflater;

    public BoardContainerAdapterDelegate(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    protected boolean isForViewType(@NonNull List<Delegatable> items, int position) {
        return items.get(position) instanceof BoardContainer;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new BoardContainerVH(inflater.inflate(R.layout.item_board_container, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull List<Delegatable> items, int position,
                                    @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {
        BoardContainerVH boardContainerVH = (BoardContainerVH) holder;

        Timber.d("check: " + items.toString());
        if (items.get(0) instanceof BoardContainer) {
            boardContainerVH.adapter.updateAdapter(((BoardContainer) items.get(0)).getBoardList());
        }
    }

    static class BoardContainerVH extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;

        BoardItemAdapter adapter;

        public BoardContainerVH(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recyclerView);

            provideRecyclerAndAdapter();
        }

        private void provideRecyclerAndAdapter() {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 3));
            recyclerView.setNestedScrollingEnabled(false);

            adapter = new BoardItemAdapter(new ArrayList<>());
            recyclerView.setAdapter(adapter);
        }
    }
}
