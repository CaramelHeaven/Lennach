package com.caramelheaven.lennach.ui.board;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
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
import com.caramelheaven.lennach.datasource.database.entity.helpers.PostFileThread;
import com.caramelheaven.lennach.utils.myOnItemClickListener;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by CaramelHeaven on 29.07.2018
 */
public class BoardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PostFileThread> threadList;
    private Set<PostFileThread> threadUniq;
    private myOnItemClickListener myOnItemClickListener;

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
        boardVH.tvDate.setText(threadList.get(i).getPostsInThreads().posts.get(0).getDate());

        if (threadList.get(i).getiFile() != null) {
            Glide.with(boardVH.ivThread.getContext())
                    .load("https://2ch.hk" + threadList.get(i).getiFile().getPath())
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
        threadList.addAll(threadUniq);
        notifyDataSetChanged();
    }

    public void setMyOnItemClickListener(com.caramelheaven.lennach.utils.myOnItemClickListener myOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }

    public String getThreadIdByPosition(int position) {
        return threadList.get(position).getPostsInThreads().iThread.getThreadId();
    }

    private class BoardVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTitle, tvDate;
        ImageView ivThread;
        CardView cardView;

        public BoardVH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tv_date);
            ivThread = itemView.findViewById(R.id.ivThread);
            cardView = itemView.findViewById(R.id.cardView);
            cardView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {
            myOnItemClickListener.onItemClick(getAdapterPosition());
        }
    }
}
