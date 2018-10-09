package com.caramelheaven.lennach.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import timber.log.Timber;

public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager layoutManager;

    public PaginationScrollListener(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

        if (!isLoading() && !isLastPage()) {
            Timber.d("visiItemCount: " + visibleItemCount + " total: " + totalItemCount + " first; " + firstVisibleItemPosition);
            Timber.d("inside if");
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0) {
                Timber.d("LoadMore");
                loadMoreItems();
            }
        }
    }

    protected abstract void loadMoreItems();

    protected abstract boolean isLoading();

    protected abstract boolean isLastPage();
}
