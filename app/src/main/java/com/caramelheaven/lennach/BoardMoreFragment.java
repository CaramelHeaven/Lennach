package com.caramelheaven.lennach;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.caramelheaven.lennach.Utils.Constants;
import com.caramelheaven.lennach.adapters.BoardAdapter;
import com.caramelheaven.lennach.data.Board;
import com.caramelheaven.lennach.database.BoardDbHelper;
import com.caramelheaven.lennach.database.BoardRealm;
import com.caramelheaven.lennach.network.ApiFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BoardMoreFragment extends BaseFragment<BoardRealm> {
    private LinearLayoutManager layoutManager;
    private BoardAdapter boardAdapter;
    private final int PAGE_SIZE = Constants.LIST_PAGE_SIZE;
    private int page = Constants.LIST_FIRST_PAGE;

    private static final String LOGS = BoardMoreFragment.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public BoardMoreFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        recyclerView = view.findViewById(R.id.fragment_recycler);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //добавляю реалмЛист, будет ли толк?
        boardAdapter = new BoardAdapter(list, getActivity());
        recyclerView.setAdapter(boardAdapter);

        if (list.size() < 1) {
            getData();
        }
        //recyclerView.addOnScrollListener(recyclerViewOnScrollListener);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && layoutManager != null) {
                    recyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            list.add(null);
                            boardAdapter.notifyItemChanged(list.size() - 1);
                            boardAdapter.notifyDataSetChanged();
                            LinearLayoutManager lm = layoutManager;
                            int lastPos = lm.findLastVisibleItemPosition();
                            int totalItem = lm.getItemCount();
                            int visibleItem = lm.getChildCount();
                            if ((visibleItem + lastPos) >= totalItem) {
                                page++;
                                loadMore();
                            }
                        }
                    });

                }
            }
        });
    }

    /*private int mDy;
    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            switch (newState) {
                case RecyclerView.SCROLL_STATE_DRAGGING:
                    break;
                case RecyclerView.SCROLL_STATE_IDLE:
                    break;
                case RecyclerView.SCROLL_STATE_SETTLING:
                    if (mDy > 0 && layoutManager != null) {
                        LinearLayoutManager linearLayoutManager = layoutManager;
                        int lastPos = linearLayoutManager.findLastVisibleItemPosition();
                        int itemCount = boardAdapter.getItemCount();
                        if (lastPos == itemCount - 1)
                            loadMore();
                    }
                    break;
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            mDy = dy;
        }
    };*/

    private void loadMore() {
        Log.i(LOGS, " LOAD MORE() CALLED");
        /*if ((page - 1) * Constants.LIST_PAGE_SIZE > list.size())
            return*/
        ;
        showList();
    }

    void getData() {
        Log.d(LOGS, "Called getData() " + page);
        if (isOnline()) {
            ApiFactory.getCheckingService()
                    .getRxBoard("pa")
                    .subscribeOn(Schedulers.io())
                    .subscribeOn(Schedulers.computation())
                    .map(Board::getThreads)
                    .observeOn(AndroidSchedulers.mainThread())
                    //.doOnComplete(this::showList)
                    .subscribe(document -> {
                        Log.i(LOGS, String.valueOf("Document: " + document));
                        BoardDbHelper dbHelper = new BoardDbHelper(document, realmUI);
                        dbHelper.saveToDatabase();
                        showList();
                    }, throwable -> showError());
        }
        /*{ //recyclerView.setAdapter(new BoardAdapter(re, getActivity()));
        showList()}, Throwable::printStackTrace);*/
    }

    private void showError() {
        //textOnline.setVisibility(View.VISIBLE);
        Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
    }

    void showList() {
        Log.i(LOGS, String.valueOf("SHOW LIST: " + list));
        BoardDbHelper dbHelper = new BoardDbHelper(list, realmUI);
        dbHelper.getFromDatabase(page, PAGE_SIZE);
        boardAdapter.notifyDataSetChanged();
    }

    private boolean isOnline() {
        ConnectivityManager manager =
                //подойдет ли getActivity?
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}