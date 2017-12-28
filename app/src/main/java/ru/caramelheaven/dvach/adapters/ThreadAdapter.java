package ru.caramelheaven.dvach.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.caramelheaven.dvach.R;
import ru.caramelheaven.dvach.data.File;
import ru.caramelheaven.dvach.data.Post;

public class ThreadAdapter extends RecyclerView.Adapter<ThreadAdapter.ViewHolder> {

    private List<Post> postList;
    Context context;

    public ThreadAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textNumberPost;
        TextView textContent;
        TextView textData;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textNumberPost = itemView.findViewById(R.id.textNumberPost);
            textContent = itemView.findViewById(R.id.textContent);
            textData = itemView.findViewById(R.id.textData);
            image = itemView.findViewById(R.id.imageViewThread);
        }


    }

    @Override
    public ThreadAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thread_elements, parent, false);
        return new ThreadAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ThreadAdapter.ViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.textName.setText(Html.fromHtml(post.getName()));
        holder.textNumberPost.setText(Html.fromHtml(String.valueOf(post.getNum())));
        holder.textContent.setText(Html.fromHtml(post.getComment()));
        holder.textData.setText(Html.fromHtml(post.getDate()));

        holder.image.setVisibility(View.GONE);
        int files = post.getFiles().size();
        if (files > 0) {
            for (File file : post.getFiles()) {
                if (file.getPath() != null) {
                    holder.image.setVisibility(View.VISIBLE);
                    String f = file.getPath();
                    Picasso.with(context).load("https://2ch.hk/" + f).into(holder.image);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
}