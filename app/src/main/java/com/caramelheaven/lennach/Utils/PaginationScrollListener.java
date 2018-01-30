package com.caramelheaven.lennach.Utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager layoutManager;
    private static final String LOGS = PaginationScrollListener.class.getSimpleName();

    public PaginationScrollListener(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItem = layoutManager.getChildCount();
        int totalItems = layoutManager.getItemCount();
        int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
        Log.i(LOGS, "VisibleItem: " + visibleItem +
                " TotalItems: " + totalItems +
                " FirstVisibleItem: " + firstVisibleItem);
        if (!isLoading() && !isLastPage()) {
            Log.i(LOGS, "isLoading state: " + isLoading() +
                    " isLastPage state: " + isLastPage());
            if ((visibleItem + firstVisibleItem) >= totalItems
                    && firstVisibleItem >= 0) {
                loadMoreItems();
            }
        }
    }

    protected abstract void loadMoreItems();

    public abstract boolean isLastPage();

    public abstract boolean isLoading();
}
