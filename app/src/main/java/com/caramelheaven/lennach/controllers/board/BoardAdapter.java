package com.caramelheaven.lennach.controllers.board;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.datasourse.model.Threads;
import com.caramelheaven.lennach.utils.OnItemClickListener;

import java.util.List;

public class BoardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String LOGS = BoardAdapter.class.getSimpleName();
    private List<Threads> threadsList;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public BoardAdapter(Context context, List<Threads> threadsList) {
        this.context = context;
        this.threadsList = threadsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_board, parent, false);
        return new BoardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BoardViewHolder boardVH = (BoardViewHolder) holder;
        Log.d(LOGS, " numberThread: " + threadsList.get(position).getNum());
        boardVH.numberThread.setText("# " + threadsList.get(position).getNum());
        boardVH.textDate.setText(threadsList.get(position).getDate());
        boardVH.textPosts.setText(String.valueOf(threadsList.get(position).getPostsCount()) + " posts");
        boardVH.textFiles.setText(String.valueOf(threadsList.get(position).getFilesCount()) + " files");
        boardVH.textSubject.setText(threadsList.get(position).getSubject());

        String fileUrl = threadsList.get(position).getFiles().get(0).getPath();
        if (fileUrl != null) {
            Glide.with(context)
                    .load("https://2ch.hk/" + fileUrl)
                    .apply(RequestOptions.centerCropTransform())
                    .into(boardVH.imageView);
            boardVH.imageView.setVisibility(View.VISIBLE);
            boardVH.progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return threadsList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private class BoardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView numberThread;
        TextView textSubject;
        TextView textDate;
        ImageView imageView;
        TextView textPosts;
        TextView textFiles;
        ProgressBar progressBar;

        BoardViewHolder(View itemView) {
            super(itemView);
            numberThread = itemView.findViewById(R.id.numberThread);
            textSubject = itemView.findViewById(R.id.textPost);
            textDate = itemView.findViewById(R.id.textDate);
            imageView = itemView.findViewById(R.id.imageView);
            textPosts = itemView.findViewById(R.id.textPosts);
            textFiles = itemView.findViewById(R.id.textFiles);
            progressBar = itemView.findViewById(R.id.spinnerImage);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
