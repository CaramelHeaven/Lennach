package com.caramelheaven.lennach.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.database.FileDB;
import com.caramelheaven.lennach.database.ThreadRealm;

import java.util.List;

import io.realm.RealmList;

import static com.bumptech.glide.request.RequestOptions.centerCropTransform;

public class ThreadAdapter extends RecyclerView.Adapter<ThreadAdapter.ViewHolder> {

    private Context context;
    private RealmList<ThreadRealm> thread;
    private static final String LOGS = ThreadAdapter.class.getSimpleName();

    public ThreadAdapter(Context context, RealmList<ThreadRealm> thread) {
        this.context = context;
        this.thread = thread;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView subject;
        TextView comment;
        TextView date;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.subject_thread);
            comment = itemView.findViewById(R.id.comment_thread);
            date = itemView.findViewById(R.id.date_thread);
            imageView = itemView.findViewById(R.id.image_thread);
        }
    }

    @Override
    public ThreadAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.thread_elements, parent, false);
        return new ThreadAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ThreadAdapter.ViewHolder holder, int position) {
        final ThreadRealm threadRealm = thread.get(position);
        if (threadRealm != null) {
            try {
                Log.d(LOGS, threadRealm.getSubject() + " ");
                Log.d(LOGS, threadRealm.getComment() + " ");
                Log.d(LOGS, threadRealm.getDate() + " ");
                holder.subject.setText(Html.fromHtml(threadRealm.getSubject()));
                holder.comment.setText(Html.fromHtml(threadRealm.getComment()));
                holder.date.setText(Html.fromHtml(threadRealm.getDate()));

                int files = threadRealm.getFiles().size();
                Log.d(LOGS, "getFiles().size(): " + threadRealm.getFiles().size());
                if (files > 0) {
                    for (FileDB file : threadRealm.getFiles()) {
                        if (!file.getPath().isEmpty()) {
                            //Here is a bug, We can download more then one image and set it on the our realm
                            holder.imageView.setVisibility(View.VISIBLE);
                            String temp = file.getPath();
                            Log.d(LOGS, "Image: " + temp);
                            Glide.with(context)
                                    .load("https://2ch.hk/" + temp)
                                    .apply(centerCropTransform())
                                    .into(holder.imageView);
                            break;
                        } else {
                            Glide.with(context).clear(holder.imageView);
                        }
                    }
                } else {
                    Log.d(LOGS, "Image not found");
                }
            } catch (NullPointerException e) {
                Log.d(LOGS, "NPE exception");
            }
        }
    }

    @Override
    public int getItemCount() {
        return thread.size();
    }
}
