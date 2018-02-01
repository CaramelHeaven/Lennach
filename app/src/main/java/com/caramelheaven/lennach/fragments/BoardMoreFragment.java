package com.caramelheaven.lennach.fragments;

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

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.Utils.Constants;
import com.caramelheaven.lennach.adapters.BoardAdapter;
import com.caramelheaven.lennach.data.Board;
import com.caramelheaven.lennach.database.BoardDbHelper;
import com.caramelheaven.lennach.database.BoardRealm;
import com.caramelheaven.lennach.network.ApiFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.RealmList;

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
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //добавляю реалмЛист, будет ли толк?
        boardAdapter = new BoardAdapter(getActivity(), list);

        recyclerView.setAdapter(boardAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*recyclerView.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                page++;

                isLoading = false;

                RealmList<BoardRealm> realms;
                Log.i(LOGS, String.valueOf("LoadMore"));
                BoardDbHelper dbHelper = new BoardDbHelper(list, realmUI);
                realms = dbHelper.getFromDatabase(page, PAGE_SIZE);

                boardAdapter.addAll(realms);
            }

            @Override
            public int getTotalPageCount() {
                return PAGE_SIZE;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });*/
        //Not working
        /*final RealmList<BoardRealm> realms = null;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0 && layoutManager != null) {
                    LinearLayoutManager lm = layoutManager;
                    int lastPos = lm.findLastVisibleItemPosition();
                    int totalItem = lm.getItemCount();
                    int visibleItem = lm.getChildCount();
                    if ((visibleItem + lastPos) >= totalItem) {
                        Log.i(LOGS, String.valueOf("LoadMore"));
                        BoardDbHelper dbHelper = new BoardDbHelper(list, realmUI);
                        dbHelper.getFromDatabase(page++, PAGE_SIZE);
                    }
                }
            }
        });
        boardAdapter.addAll(realms);*/

        if (list.size() < 1) {
            //progressBar.setVisibility(View.VISIBLE);
            getData();
            //progressBar.setVisibility(View.GONE);
        }
    }

    private void loadMore() {

        //boardAdapter.notifyDataSetChanged();
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
    }

    private void showError() {
        //textOnline.setVisibility(View.VISIBLE);
        Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
    }

    void showList() {
        Log.i(LOGS, String.valueOf("SHOW LIST: " + list));
        BoardDbHelper dbHelper = new BoardDbHelper(list, realmUI);
        dbHelper.getFromDatabase(page++, PAGE_SIZE);
        boardAdapter.notifyDataSetChanged();
    }

    private boolean isOnline() {
        ConnectivityManager manager =
                //подойдет ли getActivity?
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //realmUI.close();
    }
}