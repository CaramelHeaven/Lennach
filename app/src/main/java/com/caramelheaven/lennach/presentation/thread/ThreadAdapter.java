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

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.thread.Post;
import com.caramelheaven.lennach.utils.OnAnswerItemClickListener;
import com.caramelheaven.lennach.utils.OnTextViewLinkClickListener;

import java.util.List;

import timber.log.Timber;

public class ThreadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Post> postList;

    private OnTextViewLinkClickListener onTextViewLinkClickListener;

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

        if (postList.get(position).getFiles().size() == 0) {
            postVH.ivPicture.setVisibility(View.GONE);
            postVH.tvPictureName.setVisibility(View.GONE);
        } else {
            postVH.ivPicture.setVisibility(View.VISIBLE);
            postVH.tvPictureName.setVisibility(View.VISIBLE);
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

    public class PostVH extends RecyclerView.ViewHolder {

        TextView tvDescription, tvDate, tvPictureName;
        ImageView ivPicture;
        Button btnReply;

        PostVH(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvPictureName = itemView.findViewById(R.id.tv_picture_description);
            tvDate = itemView.findViewById(R.id.tv_number_and_date);
            ivPicture = itemView.findViewById(R.id.iv_picture_thread);
            btnReply = itemView.findViewById(R.id.btn_reply);
        }


    }

    public void setOnTextViewLinkClickListener(OnTextViewLinkClickListener
                                                       onTextViewLinkClickListener) {
        this.onTextViewLinkClickListener = onTextViewLinkClickListener;
    }
}
