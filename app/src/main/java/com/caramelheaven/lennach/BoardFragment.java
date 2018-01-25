package com.caramelheaven.lennach;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.caramelheaven.lennach.activity.BoardActivity;
import com.caramelheaven.lennach.adapters.BoardAdapter;
import com.caramelheaven.lennach.data.Board;
import com.caramelheaven.lennach.data.Thread;
import com.caramelheaven.lennach.database.BoardDbHelper;
import com.caramelheaven.lennach.database.BoardRealm;
import com.caramelheaven.lennach.network.ApiFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoardFragment extends BaseFragment<BoardRealm> {
    private ProgressBar progressBar;
    private BoardAdapter boardAdapter;
    private final int PAGE_SIZE = 40;
    private int LIST_FIRST_PAGE = 1;
    private static final String LOGS = BoardFragment.class.getSimpleName();

    public static BoardFragment newInstance() {
        return new BoardFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public BoardFragment() {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //добавляю реалмЛист, будет ли толк?
        boardAdapter = new BoardAdapter(list, getActivity());
        recyclerView.setAdapter(boardAdapter);

        //А вот здесь ответ на вопрос по запросу к интернету
        if (list.size() < 1) {
            getData(1);
        }
    }

    private void loadNextPage(int page) {
        Log.d(LOGS, "Next Page: " + page);
        getData(page);
    }


    void getData(int page) {
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
        dbHelper.getFromDatabase(PAGE_SIZE, LIST_FIRST_PAGE++);
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
