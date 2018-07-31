package com.caramelheaven.lennach.ui.board;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.datasource.database.entity.PostsInThreads;

import java.util.List;

/**
 * Created by CaramelHeaven on 29.07.2018
 */
public class BoardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PostsInThreads> thread;

    public BoardAdapter(List<PostsInThreads> thread) {
        this.thread = thread;
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
        boardVH.tvTitle.setText(Html.fromHtml(thread.get(i).iPostList.get(0).getSubject()));
        boardVH.tvDescription.setText(Html.fromHtml(thread.get(i).iPostList.get(0).get));
        //for future  holder.views.setText(Html.fromHtml(boardRealm.getDate()));
    }

    @Override
    public int getItemCount() {
        return thread.size();
    }

    public void updateAdapter(List<PostsInThreads> iThreadList) {
        thread.clear();
        thread.addAll(iThreadList);
        notifyDataSetChanged();
    }

    private class BoardVH extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDescription;
        ImageView ivThread;

        public BoardVH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivThread = itemView.findViewById(R.id.ivThread);
        }
    }
}
