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
import com.caramelheaven.lennach.datasource.database.entity.iFile;
import com.caramelheaven.lennach.ui.base.AdapterMethods;
import com.caramelheaven.lennach.utils.imageOnItemClickListener;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import timber.log.Timber;

public class ThreadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AdapterMethods<PostsHelper> {

    private List<PostsHelper> postsHelpers;
    private Set<PostsHelper> postsUnique;

    private Context context;
    private imageOnItemClickListener imageOnItemClickListener;

    public ThreadAdapter(List<PostsHelper> postsHelpers, Context context) {
        this.postsHelpers = postsHelpers;
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
        postVH.tvDescription.setText(Html.fromHtml(postsHelpers.get(i).iPost.getComment()));
        postVH.tvDate.setText(postsHelpers.get(i).iPost.getDate());
        postVH.tvCountPost.setText(String.valueOf(i));

        Timber.d("postsHelpers.get(i).iFileList.size() " + postsHelpers.get(i).iFileList.size() + " iii: " + i);
        if (postsHelpers.get(i).iFileList.size() != 0) {
            for (int q = 0; q < postsHelpers.get(i).iFileList.size(); q++) {
                ImageView imageView = new ImageView(postVH.itemView.getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, 200);
                params.setMarginStart(4);
                imageView.setLayoutParams(params);
                Glide.with(imageView.getContext())
                        .load("https://2ch.hk" + postsHelpers.get(i).iFileList.get(q).getPath())
                        .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                        .into(imageView);
                postVH.llContainer.addView(imageView);
            }
        }

        postVH.postButton.setOnClickListener(view -> {
            Intent intent = new Intent(context,MessageActivity.class);
            intent.putExtra("THREADNUMB",postsHelpers.get(i).iPost.getIdThread());
            context.startActivity(intent);
        });


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

    public void setImageOnItemClickListener(com.caramelheaven.lennach.utils.imageOnItemClickListener imageOnItemClickListener) {
        this.imageOnItemClickListener = imageOnItemClickListener;
    }

    private class PostVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvDescription, tvDate, tvCountPost;
        LinearLayout llContainer;
        Button postButton;

        public PostVH(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvCountPost = itemView.findViewById(R.id.tv_count_post);
            llContainer = itemView.findViewById(R.id.ll_container);
            llContainer.setOnClickListener(this::onClick);
            postButton = itemView.findViewById(R.id.post_btn);
        }

        @Override
        public void onClick(View view) {
            if (llContainer == view) {
                imageOnItemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }
}
