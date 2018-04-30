package com.caramelheaven.lennach.controllers.board;

import android.content.Context;
import android.databinding.DataBindingUtil;
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
import com.caramelheaven.lennach.databinding.ItemBoardBinding;
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
        ItemBoardBinding bindingItem = ItemBoardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BoardViewHolder(bindingItem.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BoardViewHolder boardVH = (BoardViewHolder) holder;
        boardVH.binding.textNumberPost.setText("# " + threadsList.get(position).getNum());
        boardVH.binding.textDate.setText(threadsList.get(position).getDate());
        boardVH.binding.textDescribe.setText(threadsList.get(position).getSubject());

        String fileUrl = threadsList.get(position).getFiles().get(0).getPath();
        if (fileUrl != null) {
            Glide.with(context)
                    .load("https://2ch.hk/" + fileUrl)
                    .apply(RequestOptions.centerCropTransform())
                    .into(boardVH.binding.imageThread);
        }
    }

    @Override
    public int getItemCount() {
        return threadsList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private class BoardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ItemBoardBinding binding;

        BoardViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            binding.executePendingBindings();
            //setOnClickListener is here
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
