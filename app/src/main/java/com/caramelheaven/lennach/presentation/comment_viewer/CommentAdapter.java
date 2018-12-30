package com.caramelheaven.lennach.presentation.comment_viewer;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.thread.Post;
import com.caramelheaven.lennach.utils.OnPostItemClickListener;

import java.util.List;

/**
 * Created by CaramelHeaven on 18:45, 15/12/2018.
 */
public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Post> postList;
    private static final int NON_TEXT = -1;

    private OnPostItemClickListener onPostItemClickListener;

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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        CommentVH commentVH = (CommentVH) viewHolder;

        commentVH.tvDescription.setText(postList.get(position).getModernComment());
        commentVH.tvDescription.setMovementMethod(LinkMovementMethod.getInstance());
        commentVH.tvNumAndDate.setText("No " + postList.get(position).getNum() + " " + postList.get(position).getDate());

        if (postList.get(position).getRepliesPostList() != null) {
            commentVH.btnReply.setText(String.valueOf(postList.get(position).getRepliesPostList().size()) + " replies");
            commentVH.btnReply.setVisibility(View.VISIBLE);
        } else {
            commentVH.btnReply.setVisibility(View.GONE);
        }

        if (postList.get(position).getFiles().size() == 0) {
            commentVH.ivPicture.setVisibility(View.GONE);
            commentVH.tvPictureName.setVisibility(View.GONE);
            commentVH.tvPictureName.setText("");
        } else {
            commentVH.ivPicture.setVisibility(View.VISIBLE);
            commentVH.tvPictureName.setVisibility(View.VISIBLE);
            commentVH.tvPictureName.setText(postList.get(position).getFiles().get(0).getDisplayNameImage());
        }

        if (postList.get(position).getFiles().size() > 0) {
            Glide.with(commentVH.ivPicture)
                    .load("https://2ch.hk" + postList.get(position).getFiles().get(0).getThumbnail())
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                    .into(commentVH.ivPicture);
        } else {
            Glide.with(commentVH.ivPicture.getContext()).clear(commentVH.ivPicture);
        }

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (postList.get(position).getComment().equals("")) {
            return NON_TEXT;
        } else {
            return super.getItemViewType(position);
        }
    }

    public void updateAdapter(List<Post> posts) {
        postList = posts;
        notifyDataSetChanged();
    }

    public Post getItemByPosition(int pos) {
        return postList.get(pos);
    }

    class CommentVH extends RecyclerView.ViewHolder {
        TextView tvDescription, tvNumAndDate, tvPictureName;
        ImageView ivPicture;
        Button btnReply;

        public CommentVH(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvPictureName = itemView.findViewById(R.id.tv_picture_description);
            tvNumAndDate = itemView.findViewById(R.id.tv_number_and_date);
            ivPicture = itemView.findViewById(R.id.iv_picture_thread);
            btnReply = itemView.findViewById(R.id.btn_reply);

            btnReply.setOnClickListener(v ->
                    onPostItemClickListener.onBtnReplyClick(getAdapterPosition()));
        }
    }

    public void setOnPostItemClickListener(OnPostItemClickListener onPostItemClickListener) {
        this.onPostItemClickListener = onPostItemClickListener;
    }
}
