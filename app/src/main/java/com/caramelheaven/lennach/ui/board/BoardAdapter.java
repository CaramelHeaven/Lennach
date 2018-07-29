package com.caramelheaven.lennach.ui.board;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.datasource.database.entity.iThread;

import java.util.List;

/**
 * Created by CaramelHeaven on 29.07.2018
 */
public class BoardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<iThread> iThreads;

    public BoardAdapter(List<iThread> iThreads) {
        this.iThreads = iThreads;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_board, viewGroup, false);
        return new BoardVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        BoardVH boardVH = (BoardVH) viewHolder;
        boardVH.tvThreadNum.setText(iThreads.get(i).getThreadId());
        //for future  holder.views.setText(Html.fromHtml(boardRealm.getDate()));
    }

    @Override
    public int getItemCount() {
        return iThreads.size();
    }

    public void updateAdapter(List<iThread> iThreadList) {
        iThreads.clear();
        iThreads.addAll(iThreadList);
        notifyDataSetChanged();
    }

    private class BoardVH extends RecyclerView.ViewHolder {

        TextView tvThreadNum;

        public BoardVH(@NonNull View itemView) {
            super(itemView);
            tvThreadNum = itemView.findViewById(R.id.tvThreadNum);
        }
    }
}
