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

import timber.log.Timber;

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
        boardVH.tvTitle.setText(Html.fromHtml(thread.get(i).posts.get(0).getSubject()));
        boardVH.tvDescription.setText(Html.fromHtml(thread.get(i).posts.get(0).getComment()));
        boardVH.tvDate.setText(thread.get(i).posts.get(i).getComment());
        boardVH.tvCountPosts.setText(String.valueOf(thread.get(i).iThread.getPostsCount()));
        boardVH.tvCountFiles.setText(String.valueOf(thread.get(i).iThread.getFilesCount()));
        Timber.d("files: " + thread.get(i).files.get(0).getDisplayName());
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

        TextView tvTitle, tvDescription, tvDate, tvCountFiles, tvCountPosts;
        ImageView ivThread;

        public BoardVH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvCountFiles = itemView.findViewById(R.id.tvCountFiles);
            tvCountPosts = itemView.findViewById(R.id.tvCountPosts);
            ivThread = itemView.findViewById(R.id.ivThread);
        }
    }
}
