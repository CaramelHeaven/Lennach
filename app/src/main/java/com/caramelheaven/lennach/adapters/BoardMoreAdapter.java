package com.caramelheaven.lennach.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
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
import com.caramelheaven.lennach.database.BoardRealm;
import com.caramelheaven.lennach.database.FileDB;

import io.realm.RealmList;

import static com.bumptech.glide.request.RequestOptions.centerCropTransform;

/**
 * Created by Lion on 26.01.2018.
 */

public class BoardMoreAdapter extends RecyclerView.Adapter {

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    private static final String LOGS = BoardMoreAdapter.class.getSimpleName();
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private Context context;
    private RealmList<BoardRealm> threads;

    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;

    public BoardMoreAdapter(RealmList<BoardRealm> threads, Context context, RecyclerView recyclerView) {
        this.context = context;
        this.threads = threads;
        //Very awful
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        // End has been reached
                        // Do something
                        if (onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                        }
                        loading = true;
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return threads.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        if (viewType == VIEW_ITEM) {
            View view = LayoutInflater.
                    from(parent.getContext()).inflate(R.layout.board_elements, parent, false);
            viewHolder = new ThreadsViewHolder(view);
        } else {
            View view = LayoutInflater.
                    from(parent.getContext()).inflate(R.layout.progress_bar_item, parent, false);
            viewHolder = new ProgressBarViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ThreadsViewHolder) {
            final BoardRealm boardRealm = threads.get(position);
            Log.d(LOGS, threads.get(position) + " ");
            if (boardRealm != null) {
                ((ThreadsViewHolder) holder).subject.setText(Html.fromHtml(boardRealm.getSubject()));
                Log.d(LOGS, boardRealm.getSubject() + " ");
                ((ThreadsViewHolder) holder).comment.setText(Html.fromHtml(boardRealm.getComment()));
                Log.d(LOGS, boardRealm.getComment() + " ");
                ((ThreadsViewHolder) holder).views.setText(Html.fromHtml(boardRealm.getDate()));
                Log.d(LOGS, boardRealm.getDate() + " ");
                ((ThreadsViewHolder) holder).imageView.setVisibility(View.GONE);
            } else {
                Toast.makeText(context, "Failed onBindViewHolder in BoardAdapter", Toast.LENGTH_SHORT).show();
            }

            int files = boardRealm.getFiles().size();
            Log.d(LOGS, "boardRealm.getFiles().size(): " + boardRealm.getFiles().size());
            if (files > 0) {
                for (FileDB file : boardRealm.getFiles()) {
                    if (!file.getPath().isEmpty()) {
                        //Here is a bug, We can download more then one image and set it on the our realm
                        ((ThreadsViewHolder) holder).imageView.setVisibility(View.VISIBLE);
                        String temp = file.getPath();
                        Log.d(LOGS, "Image: " + temp);
                        Glide.with(context)
                                .load("https://2ch.hk/" + temp)
                                .apply(centerCropTransform())
                                .into(((ThreadsViewHolder) holder).imageView);
                        break;
                    } else {
                        Glide.with(context).clear(((ThreadsViewHolder) holder).imageView);
                    }
                }
            } else {
                Log.d(LOGS, "Image not found");
            }
        } else {
            ((ProgressBarViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    public void setLoaded() {
        loading = false;
    }

    @Override
    public int getItemCount() {
        return threads.size();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public static class ThreadsViewHolder extends RecyclerView.ViewHolder {
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

    public static class ProgressBarViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        public ProgressBarViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress_bar_item);
        }
    }
}
