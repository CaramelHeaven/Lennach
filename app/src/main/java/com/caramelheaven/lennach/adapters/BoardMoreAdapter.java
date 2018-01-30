/*
package com.caramelheaven.lennach.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.data.Thread;
import com.caramelheaven.lennach.database.BoardRealm;
import com.caramelheaven.lennach.database.FileDB;

import io.realm.RealmList;

import static com.bumptech.glide.request.RequestOptions.centerCropTransform;

public class BoardMoreAdapter extends RecyclerView.Adapter {
    private static final String LOGS = BoardMoreAdapter.class.getSimpleName();
    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private RealmList<BoardRealm> threads;
    private Context context;

    //Checking
    private boolean isLoadingAdded = false;

    public BoardMoreAdapter(Context context, RealmList<BoardRealm> threads) {
        this.context = context;
        this.threads = threads;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case ITEM:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_elements, parent, false);
                viewHolder = new ThreadsViewHolder(view);
                break;
            case LOADING:
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_bar_item, parent, false);
                viewHolder = new LoadingViewHolder(view1);
                break;

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BoardRealm boardRealm = threads.get(position);
        switch (getItemViewType(position)) {
            case ITEM:
                final ThreadsViewHolder thread = (ThreadsViewHolder) holder;

                if (boardRealm != null) {
                    thread.subject.setText(Html.fromHtml(boardRealm.getSubject()));
                    Log.d(LOGS, boardRealm.getSubject() + " ");
                    thread.comment.setText(Html.fromHtml(boardRealm.getComment()));
                    Log.d(LOGS, boardRealm.getComment() + " ");
                    thread.views.setText(Html.fromHtml(boardRealm.getDate()));
                    Log.d(LOGS, boardRealm.getDate() + " ");
                    thread.imageView.setVisibility(View.GONE);
                } else {
                    Toast.makeText(context, "Failed onBindViewHolder in BoardAdapter", Toast.LENGTH_SHORT).show();
                }
                //Bug with null - getFiles
                int files = boardRealm.getFiles().size();
                Log.d(LOGS, "boardRealm.getFiles().size(): " + boardRealm.getFiles().size());
                if (files > 0) {
                    for (FileDB file : boardRealm.getFiles()) {
                        if (!file.getPath().isEmpty()) {
                            //Here is a bug, We can download more then one image and set it on the our realm
                            thread.imageView.setVisibility(View.VISIBLE);
                            String temp = file.getPath();
                            Log.d(LOGS, "Image: " + temp);
                            Glide.with(context)
                                    .load("https://2ch.hk/" + temp)
                                    .apply(centerCropTransform())
                                    .into(thread.imageView);
                            break;
                        } else {
                            Glide.with(context).clear(thread.imageView);
                        }
                    }
                } else {
                    Log.d(LOGS, "Image not found");
                }
                break;
            case LOADING:
                LoadingViewHolder loading = (LoadingViewHolder) holder;
                loading.progressBar.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return threads == null ? Log.i("BoardMoreAdapter", " : size = 0") : threads.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == threads.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    private class ThreadsViewHolder extends RecyclerView.ViewHolder {
        TextView subject;
        TextView comment;
        TextView views;
        ImageView imageView;

        ThreadsViewHolder(View itemView) {
            super(itemView);
            views = itemView.findViewById(R.id.views);
            subject = itemView.findViewById(R.id.subject);
            comment = itemView.findViewById(R.id.comment);
            imageView = itemView.findViewById(R.id.image_poster);
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress_bar_item);
        }
    }

    ////// HELPERS //////////
    public void add(BoardRealm boardRealm) {
        threads.add(boardRealm);
        notifyItemInserted(threads.size() - 1);
    }

    public void addAll(RealmList<BoardRealm> list) {
        threads.clear();//important - without it we can get java.util.ConcurrentModificationException
        for (BoardRealm board : list) {
            add(board);
        }
        notifyDataSetChanged();
    }



    public void addLoading() {
        isLoadingAdded = true;
        add(new BoardRealm());
    }

    public void removeLoading() {
        isLoadingAdded = false;

        int position = threads.size() - 1;
        BoardRealm board = getItem(position);

        if (board != null) {
            threads.remove(position);
            Log.i(LOGS, "Здесь что ли обрывается?: " + position + " board null? : " + board);
            notifyItemRemoved(position);
        }
    }

    public BoardRealm getItem(int position) {
        return threads.get(position);
    }
}
*/
