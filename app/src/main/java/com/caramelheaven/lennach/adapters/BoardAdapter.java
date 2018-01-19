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
import com.caramelheaven.lennach.database.BoardDB;
import com.caramelheaven.lennach.database.FileDB;

import io.realm.RealmList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private Context context;
    private RealmList<BoardDB> threads;
    private static final String LOGS = BoardAdapter.class.getSimpleName();

    public BoardAdapter(RealmList<BoardDB> threads, Context context) {
        this.context = context;
        this.threads = threads;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView subject;
        TextView comment;
        TextView views;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            views = itemView.findViewById(R.id.views);
            subject = itemView.findViewById(R.id.subject);
            comment = itemView.findViewById(R.id.comment);
            imageView = itemView.findViewById(R.id.image_poster);
        }
    }

    @Override
    public BoardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_elements, parent, false);
        return new BoardAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final BoardDB boardDB = threads.get(position);
        Log.d(LOGS, threads.get(position) + " ");

        holder.subject.setText(Html.fromHtml(boardDB.getSubject()));
        Log.d(LOGS, boardDB.getSubject() + " ");
        holder.comment.setText(Html.fromHtml(boardDB.getComment()));
        Log.d(LOGS, boardDB.getComment() + " ");
        holder.views.setText(Html.fromHtml(boardDB.getDate()));
        Log.d(LOGS, boardDB.getDate() + " ");
        holder.imageView.setVisibility(View.GONE);

        int files = boardDB.getFiles().size();
        if (files > 0) {
            for (FileDB file : boardDB.getFiles()) {
                if (file.getPath() != null) {
                    holder.imageView.setVisibility(View.VISIBLE);
                    String stringFile = file.getPath();
                    Log.d(LOGS, stringFile + " ");
                    Glide.with(context)
                            .load("https://2ch.hk/" + stringFile)
                            .into(holder.imageView);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return threads.size();
    }

    public void changeDataSet(RealmList<BoardDB> threadViewDBS) {
        threads.clear();
        threads.addAll(threadViewDBS);
        notifyDataSetChanged();
    }
}