package com.caramelheaven.lennach.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.database.ThreadRealm;

import java.util.List;

import io.realm.RealmList;

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

        ViewHolder(View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.subject_thread);
            comment = itemView.findViewById(R.id.comment_thread);
            date = itemView.findViewById(R.id.date_thread);
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
                holder.subject.setText(Html.fromHtml(threadRealm.getPosts().get(position).getSubject()));
                holder.comment.setText(Html.fromHtml(threadRealm.getPosts().get(position).getComment()));
                holder.date.setText(Html.fromHtml(threadRealm.getPosts().get(position).getDate()));
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
