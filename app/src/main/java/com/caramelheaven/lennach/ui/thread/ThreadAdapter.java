package com.caramelheaven.lennach.ui.thread;

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
import com.caramelheaven.lennach.datasource.database.entity.helpers.PostsHelper;
import com.caramelheaven.lennach.ui.base.AdapterMethods;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ThreadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AdapterMethods<PostsHelper> {

    private List<PostsHelper> postsHelpers;
    private Set<PostsHelper> postsUnique;

    public ThreadAdapter(List<PostsHelper> postsHelpers) {
        this.postsHelpers = postsHelpers;
        postsUnique = new LinkedHashSet<>();
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
        postVH.tvDescription.setText(Html.fromHtml(postsHelpers.get(i).iPost.getComment()));
        postVH.tvDate.setText(postsHelpers.get(i).iPost.getDate());
        postVH.tvCountPost.setText(String.valueOf(i));

        if (postsHelpers.get(i).iFileList.size() != 0) {
            Glide.with(postVH.ivPost.getContext())
                    .load("https://2ch.hk" + postsHelpers.get(i).iFileList.get(0).getPath())
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(postVH.ivPost);
        } else {
            postVH.ivPost.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return postsHelpers.size();
    }

    @Override
    public void updateAdapter(List<PostsHelper> models) {
        postsUnique.addAll(models);
        if (postsHelpers.size() != 0)
            postsHelpers.clear();
        postsHelpers.addAll(postsUnique);
        notifyDataSetChanged();
    }

    @Override
    public PostsHelper getItemByPosition(int position) {
        return postsHelpers.get(position);
    }

    @Override
    public List<PostsHelper> getItems() {
        return postsHelpers;
    }

    private class PostVH extends RecyclerView.ViewHolder {

        TextView tvDescription, tvDate, tvCountPost;
        ImageView ivPost;

        public PostVH(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvDate = itemView.findViewById(R.id.tv_date);
            ivPost = itemView.findViewById(R.id.iv_post);
            tvCountPost = itemView.findViewById(R.id.tv_count_post);
        }
    }
}
