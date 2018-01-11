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
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class BoardAdapterRealm extends RealmRecyclerViewAdapter<BoardDB, BoardAdapterRealm.ViewHolder> {
    private Context context;
    private static final String LOGS = BoardAdapter.class.getSimpleName();

    public BoardAdapterRealm(OrderedRealmCollection<BoardDB> boardDB, Context context) {
        super(boardDB, true);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.board_elements, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final BoardDB board = getData().get(position);

        holder.subject.setText(Html.fromHtml(board.getSubject()));
        Log.d(LOGS, board.getSubject() + " ");
        holder.comment.setText(Html.fromHtml(board.getComment()));
        Log.d(LOGS, board.getComment() + " ");
        holder.views.setText(Html.fromHtml(board.getDate()));
        Log.d(LOGS, board.getDate() + " ");
        holder.imageView.setVisibility(View.GONE);

        int files = board.getFiles().size();
        if (files > 0) {
            for (FileDB file : board.getFiles()) {
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

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView subject;
        TextView comment;
        TextView views;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            views = itemView.findViewById(R.id.views);
            subject = itemView.findViewById(R.id.subject);
            comment = itemView.findViewById(R.id.comment);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
