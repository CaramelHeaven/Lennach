package com.caramelheaven.lennach.ui.thread;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.datasource.database.entity.helpers.PostsHelper;
import com.caramelheaven.lennach.datasource.model.Post;
import com.caramelheaven.lennach.ui.base.AdapterMethods;
import com.caramelheaven.lennach.utils.imageOnItemClickListener;
import com.caramelheaven.lennach.utils.item_touch.ItemTouchCallback;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import timber.log.Timber;

public class ThreadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements AdapterMethods<Post> {

    private List<Post> posts;
    private Set<Post> postsUnique;

    private Context context;
    private imageOnItemClickListener imageOnItemClickListener;
    private ItemTouchCallback itemTouchCallback;

    public ThreadAdapter(List<Post> posts) {
        this.posts = posts;
        postsUnique = new LinkedHashSet<>();
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_post, viewGroup, false);
        return new PostVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        PostVH postVH = (PostVH) viewHolder;
        postVH.tvDescription.setText(Html.fromHtml(posts.get(i).getComment()));
        postVH.tvDate.setText(posts.get(i).getDate());
        postVH.tvCountPost.setText(String.valueOf(i));

        Timber.d("postsHelpers.get(i).iFileList.size() " + posts.get(i).getFiles().size() + " iii: " + i);
        if (posts.get(i).getFiles().size() != 0) {
            Glide.with(postVH.ivPicture.getContext())
                    .load("https://2ch.hk" + posts.get(i).getFiles().get(0).getPath())
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(postVH.ivPicture);
        }


    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    @Override
    public void updateAdapter(List<Post> models) {
        postsUnique.addAll(models);
        if (posts.size() != 0)
            posts.clear();
        posts.addAll(postsUnique);
        notifyDataSetChanged();
    }

    @Override
    public Post getItemByPosition(int position) {
        return posts.get(position);
    }

    @Override
    public List<Post> getItems() {
        return posts;
    }

    public void setImageOnItemClickListener(com.caramelheaven.lennach.utils.imageOnItemClickListener imageOnItemClickListener) {
        this.imageOnItemClickListener = imageOnItemClickListener;
    }

    public void sendCallbackFromSwipe(int position) {
        itemTouchCallback.sendAnswer(posts.get(position));
    }

    public class PostVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvDescription, tvDate, tvCountPost;
        ImageView ivPicture;

        public PostVH(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvCountPost = itemView.findViewById(R.id.tv_count_post);
            ivPicture = itemView.findViewById(R.id.iv_picture_thread);
            ivPicture.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {
            imageOnItemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public void setItemTouchCallback(ItemTouchCallback itemTouchCallback) {
        this.itemTouchCallback = itemTouchCallback;
    }
}
