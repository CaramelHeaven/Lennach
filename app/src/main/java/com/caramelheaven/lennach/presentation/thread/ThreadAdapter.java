package com.caramelheaven.lennach.presentation.thread;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.thread_viewer.Post;
import com.caramelheaven.lennach.presentation.common.AdapterMethods;
import com.caramelheaven.lennach.utils.item_touch.ItemTouchCallback;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ThreadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements AdapterMethods<Post> {

    private List<Post> postList;
    private Set<Post> postsUnique;

    private ItemTouchCallback<Post> itemTouchCallback;

    public ThreadAdapter(List<Post> postList) {
        this.postList = postList;
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
        postVH.tvDescription.setText(Html.fromHtml(postList.get(i).getComment()));
        postVH.tvDate.setText(postList.get(i).getDate());
        postVH.tvCountPost.setText(String.valueOf(i));

        if (postList.get(i).getFiles().size() != 0) {
            Glide.with(postVH.ivPicture.getContext())
                    .load("https://2ch.hk" + postList.get(i).getFiles().get(0).getThumbnail())
                    .apply(new RequestOptions().override(150, 150))
                    .into(postVH.ivPicture);
        }
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    @Override
    public void updateAdapter(List<Post> items) {
        postsUnique.addAll(items);
        postList.clear();
        postList.addAll(postsUnique);
        notifyDataSetChanged();
    }

    @Override
    public Post getItemByPosition(int position) {
        return postList.get(position);
    }

    public class PostVH extends RecyclerView.ViewHolder {

        TextView tvDescription, tvDate, tvCountPost;
        ImageView ivPicture;

        public PostVH(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvCountPost = itemView.findViewById(R.id.tv_count_post);
            ivPicture = itemView.findViewById(R.id.iv_picture_thread);
        }
    }

    public void sendCallbackFromSwipe(int position) {
        itemTouchCallback.sendAnswer(postList.get(position));
    }

    public void setItemTouchCallback(ItemTouchCallback<Post> itemTouchCallback) {
        this.itemTouchCallback = itemTouchCallback;
    }
}
