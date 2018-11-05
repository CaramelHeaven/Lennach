package com.caramelheaven.lennach.presentation.main.saved_history;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.board_viewer.Usenet;
import com.caramelheaven.lennach.presentation.common.AdapterMethods;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class HistoryRecentlyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AdapterMethods<Usenet> {

    private List<Usenet> usenetList;
    private Set<Usenet> usenetUnique;

    public HistoryRecentlyAdapter(List<Usenet> usenetList) {
        this.usenetList = usenetList;
        usenetUnique = new LinkedHashSet<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history_favourite, viewGroup, false);
        return new RecentlyVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return usenetList.size();
    }

    @Override
    public void updateAdapter(List<Usenet> items) {
        usenetUnique.addAll(items);
        usenetList.clear();
        usenetList.addAll(usenetUnique);
        notifyDataSetChanged();
    }

    @Override
    public Usenet getItemByPosition(int position) {
        return null;
    }

    class RecentlyVH extends RecyclerView.ViewHolder {

        public RecentlyVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
