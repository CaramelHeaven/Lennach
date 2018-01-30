package com.caramelheaven.lennach.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    public BoardAdapter(Context context, RealmList<BoardRealm> threads) {
        this.context = context;
        this.threads = threads;
        Log.i(LOGS, String.valueOf(threads));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView subject;
        TextView comment;
        TextView views;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layoutThread);
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
            //Log.d(LOGS, boardRealm.getSubject() + " ");
            holder.comment.setText(Html.fromHtml(boardRealm.getComment()));
            //Log.d(LOGS, boardRealm.getComment() + " ");
            holder.views.setText(Html.fromHtml(boardRealm.getDate()));
            //Log.d(LOGS, boardRealm.getDate() + " ");
            holder.imageView.setVisibility(View.GONE);
        } else {
            Toast.makeText(context, "Failed onBindViewHolder in BoardAdapter", Toast.LENGTH_SHORT).show();
        }

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked: " + threads.get(position), Toast.LENGTH_SHORT).show();
            }
        });

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

    /*public void add(BoardRealm boardRealm) {
        threads.clear();
        threads.add(boardRealm);
        notifyDataSetChanged();
        //notifyItemInserted(threads.size() - 1);
    }*/

    /*public void addAll(RealmList<BoardRealm> list) {
        //important - without it we can get java.util.ConcurrentModificationException
        *//*for (BoardRealm board : list) {
            add(board);
        }*//*
        //threads.clear();
        Log.i(LOGS, "Size list: " + list.size());
        //threads.addAll(list);
        //threads.clear();
        Iterator<BoardRealm> iter = list.iterator();
        while (iter.hasNext()) {
            BoardRealm board = iter.next();
            Log.i(LOGS, "Method addAll: " + board);
            threads.add(board);
            notifyItemInserted(threads.size() - 1);
        }
        Log.i(LOGS, "Size threads: " + threads.size());
        notifyDataSetChanged();
    }*/

    public void add(BoardRealm item) {
        threads.add(item);
        notifyItemInserted(threads.size() - 1);
    }

    public void addAll(RealmList<BoardRealm> items) {
        for (BoardRealm item : items) {
            add(item);
        }
        notifyDataSetChanged();
    }
    public interface OnItemClickListener {
        void onItemClick(BoardRealm item);
    }

}
