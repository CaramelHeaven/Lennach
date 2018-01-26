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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.database.BoardRealm;
import com.caramelheaven.lennach.database.FileDB;

import io.realm.RealmList;

import static com.bumptech.glide.request.RequestOptions.centerCropTransform;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private Context context;
    private RealmList<BoardRealm> threads;
    private static final String LOGS = BoardAdapter.class.getSimpleName();

    public BoardAdapter(RealmList<BoardRealm> threads, Context context) {
        this.context = context;
        Log.i(LOGS, String.valueOf(threads));
        this.threads = threads;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView subject;
        TextView comment;
        TextView views;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            views = itemView.findViewById(R.id.views);
            subject = itemView.findViewById(R.id.subject);
            comment = itemView.findViewById(R.id.comment);
            imageView = itemView.findViewById(R.id.image_poster);
        }
    }

    @Override
    public BoardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.
                from(parent.getContext()).inflate(R.layout.board_elements, parent, false);
        return new BoardAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final BoardRealm boardRealm = threads.get(position);
        Log.d(LOGS, threads.get(position) + " ");
        if (boardRealm != null) {
            holder.subject.setText(Html.fromHtml(boardRealm.getSubject()));
            Log.d(LOGS, boardRealm.getSubject() + " ");
            holder.comment.setText(Html.fromHtml(boardRealm.getComment()));
            Log.d(LOGS, boardRealm.getComment() + " ");
            holder.views.setText(Html.fromHtml(boardRealm.getDate()));
            Log.d(LOGS, boardRealm.getDate() + " ");
            holder.imageView.setVisibility(View.GONE);
        } else {
            Toast.makeText(context, "Failed onBindViewHolder in BoardAdapter", Toast.LENGTH_SHORT).show();
        }


        int files = boardRealm.getFiles().size();
        Log.d(LOGS, "boardRealm.getFiles().size(): " + boardRealm.getFiles().size());
        if (files > 0) {
            for (FileDB file : boardRealm.getFiles()) {
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
    }

    @Override
    public int getItemCount() {
        return threads.size();
    }
}