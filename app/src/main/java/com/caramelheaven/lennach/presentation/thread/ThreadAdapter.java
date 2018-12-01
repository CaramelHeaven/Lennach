package com.caramelheaven.lennach.presentation.thread;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.caramelheaven.lennach.models.model.thread.Post;

import java.util.List;

public class ThreadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Post opPost;
    private List<Post> postList;

    private int mainPost = 0;

    public ThreadAdapter(Post opPost, List<Post> postList) {
        this.opPost = opPost;
        this.postList = postList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == mainPost) {

        } else {

        }
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
    public int getItemViewType(int position) {
        if (mainPost == position) {
            return mainPost;
        } else {
            return position;
        }
    }

    @Override
    public int getItemCount() {
        return postList.size() + 1;
    }

    public class MainPostVH extends RecyclerView.ViewHolder {

        public MainPostVH(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class PostVH extends RecyclerView.ViewHolder {

        public PostVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
