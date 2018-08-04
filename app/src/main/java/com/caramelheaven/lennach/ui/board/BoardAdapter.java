package com.caramelheaven.lennach.ui.board;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.datasource.database.entity.PostFileThread;
import com.caramelheaven.lennach.datasource.database.entity.PostsInThreads;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 29.07.2018
 */
public class BoardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PostFileThread> threadList;
    private Set<PostFileThread> threadUniq;

    public BoardAdapter(List<PostFileThread> threadList) {
        this.threadList = threadList;
        threadUniq = new LinkedHashSet<>();
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
        boardVH.tvTitle.setText(Html.fromHtml(threadList.get(i).getPostsInThreads().posts.get(0).getSubject()));
        boardVH.tvDescription.setText(Html.fromHtml(threadList.get(i).getPostsInThreads().posts.get(0).getComment()));
        boardVH.tvDate.setText(threadList.get(i).getPostsInThreads().posts.get(i).getComment());
        boardVH.tvCountPosts.setText(String.valueOf(threadList.get(i).getPostsInThreads().iThread.getPostsCount()));
        boardVH.tvCountFiles.setText(String.valueOf(threadList.get(i).getPostsInThreads().iThread.getFilesCount()));

        if (threadList.get(i).getiFile() != null) {
            Glide.with(boardVH.ivThread.getContext())
                    .load("https://2ch.hk" + threadList.get(i).getiFile().getPath())
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .apply(new RequestOptions().override(150, 150))
                    .into(boardVH.ivThread);
        }
    }

    @Override
    public int getItemCount() {
        return threadList.size();
    }

    public void updateAdapter(List<PostFileThread> iThreadList) {
        threadUniq.addAll(iThreadList);
        threadList.clear();
        threadList.addAll(iThreadList);
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
