package com.caramelheaven.lennach.presentation.comment_viewer;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.thread.Post;

import java.util.List;

/**
 * Created by CaramelHeaven on 18:45, 15/12/2018.
 */
public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Post> postList;

    public CommentAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_post, viewGroup, false);

        return new CommentVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        CommentVH commentVH = (CommentVH) viewHolder;

        commentVH.tvDescription.setText(postList.get(i).getModernComment());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void updateAdapter(List<Post> posts) {
        postList = posts;
        notifyDataSetChanged();
    }

    class CommentVH extends RecyclerView.ViewHolder {
        TextView tvDescription, tvDate, tvCountPost, tvPictureName;
        ImageView ivPicture;
        Button btnReply;

        public CommentVH(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvPictureName = itemView.findViewById(R.id.tv_picture_description);
            tvDate = itemView.findViewById(R.id.tv_number_and_date);
            ivPicture = itemView.findViewById(R.id.iv_picture_thread);
            btnReply = itemView.findViewById(R.id.btn_reply);
        }
    }
}
