package com.caramelheaven.lennach.presentation.thread;

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
import com.caramelheaven.lennach.utils.OnItemTouchCallback;
import com.caramelheaven.lennach.utils.OnPostItemClickListener;

import java.util.List;

import timber.log.Timber;

public class ThreadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Post> postList;

    private OnPostItemClickListener onPostItemClickListener;
    private OnItemTouchCallback<Post> onItemTouchCallback;

    public ThreadAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_post, viewGroup, false);

        return new PostVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        PostVH postVH = (PostVH) viewHolder;

        postVH.tvDescription.setText(postList.get(position).getModernComment());
        postVH.tvDescription.setMovementMethod(LinkMovementMethod.getInstance());
        postVH.tvNumberAndDate.setText("No " + postList.get(position).getNum() + " " + postList.get(position).getDate());

        if (postList.get(position).getRepliesPostList() != null) {
            postVH.btnReply.setText(String.valueOf(postList.get(position).getRepliesPostList().size()) + " replies");
            postVH.btnReply.setVisibility(View.VISIBLE);
        } else {
            postVH.btnReply.setVisibility(View.GONE);
        }

        if (postList.get(position).getFiles().size() == 0) {
            postVH.ivPicture.setVisibility(View.GONE);
            postVH.tvPictureName.setVisibility(View.GONE);
        } else {
            postVH.ivPicture.setVisibility(View.VISIBLE);
            postVH.tvPictureName.setVisibility(View.VISIBLE);
        }

        if (postList.get(position).getFiles().size() > 0) {
            Glide.with(postVH.ivPicture)
                    .load("https://2ch.hk" + postList.get(position).getFiles().get(0).getThumbnail())
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                    .into(postVH.ivPicture);
        } else {
            Glide.with(postVH.ivPicture.getContext()).clear(postVH.ivPicture);
        }
    }

    public void updateAdapter(List<Post> posts) {
        postList.addAll(posts);

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void clear() {
        postList.clear();

        notifyDataSetChanged();
    }

    public Post getItemByPosition(int pos) {
        return postList.get(pos);
    }

    public class PostVH extends RecyclerView.ViewHolder {

        TextView tvDescription, tvNumberAndDate, tvPictureName;
        ImageView ivPicture;
        Button btnReply;

        PostVH(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvPictureName = itemView.findViewById(R.id.tv_picture_description);
            tvNumberAndDate = itemView.findViewById(R.id.tv_number_and_date);
            ivPicture = itemView.findViewById(R.id.iv_picture_thread);
            btnReply = itemView.findViewById(R.id.btn_reply);

            btnReply.setOnClickListener(v -> {
                Timber.d("btn was clicked");
                onPostItemClickListener.onBtnReplyClick(getAdapterPosition());
            });
        }
    }

    public void itemTouchCallback(int pos) {
        onItemTouchCallback.onTouchItem(postList.get(pos));
    }

    public void setOnPostItemClickListener(OnPostItemClickListener onPostItemClickListener) {
        this.onPostItemClickListener = onPostItemClickListener;
    }

    public void setOnItemTouchCallback(OnItemTouchCallback<Post> onItemTouchCallback) {
        this.onItemTouchCallback = onItemTouchCallback;
    }
}
