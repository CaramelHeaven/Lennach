package com.caramelheaven.lennach.presentation.thread;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.thread.Post;

import java.util.List;

public class ThreadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Post> postList;

    //position for get item from postList and calculate ClickableSpans
    private int positionOnCreate = 0;

    public ThreadAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        System.out.println("pos onCreateViewHolder: " + positionOnCreate);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_post, viewGroup, false);

        positionOnCreate++;

        return new PostVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        PostVH postVH = (PostVH) viewHolder;
        postVH.tvDescription.setText(Html.fromHtml(postList.get(position).getComment()));

        System.out.println("toString: " + postList.get(position).getCutIndexWordList().toString());
//        String
//
//        for (String spl : split) {
//            if (spl.contains(">>")) {
//                massiveLinks.add(spl);
//            }
//        }
//
//        ClickableSpan clickableSpan = new ClickableSpan() {
//            @Override
//            public void onClick(View textView) {
//                Timber.d("click");
//            }
//
//            @Override
//            public void updateDrawState(TextPaint ds) {
//                super.updateDrawState(ds);
//                ds.setUnderlineText(false);
//            }
//        };
//
//        ss.setSpan(clickableSpan, 0, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        ss.setSpan(clickableSpan, 20, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        postVH.tvDescription.setText(ss);

        // postVH.tvDescription.setMovementMethod(LinkMovementMethod.getInstance());
        // postVH.tvDate.setText(postList.get(i).getDate());
        //postVH.tvCountPost.setText(String.valueOf(i));
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

        TextView tvDescription, tvDate, tvCountPost;
        ImageView ivPicture;

        public PostVH(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tv_description);
            // tvDate = itemView.findViewById(R.id.tv_date);
            // tvCountPost = itemView.findViewById(R.id.tv_count_post);
            ivPicture = itemView.findViewById(R.id.iv_picture_thread);
        }
    }
}
