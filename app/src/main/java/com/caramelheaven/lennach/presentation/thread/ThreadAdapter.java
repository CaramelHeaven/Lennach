package com.caramelheaven.lennach.presentation.thread;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.caramelheaven.lennach.models.model.thread.Post;

import java.util.List;

public class ThreadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Post> postList;

    public ThreadAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    public void updateAdapter(List<Post> posts) {
        postList.clear();
        postList.addAll(posts);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class PostVH extends RecyclerView.ViewHolder {

        public PostVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
