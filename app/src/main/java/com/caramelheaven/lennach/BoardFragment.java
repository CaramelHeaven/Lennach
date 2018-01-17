package com.caramelheaven.lennach;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.caramelheaven.lennach.activity.BoardActivity;
import com.caramelheaven.lennach.adapters.BoardAdapter;
import com.caramelheaven.lennach.data.Board;
import com.caramelheaven.lennach.database.BoardDB;
import com.caramelheaven.lennach.network.ApiFactory;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class BoardFragment extends BaseFragment<BoardDB> {

    private BoardAdapter boardAdapter;

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

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //добавляю реалмЛист, будет ли толк?
        boardAdapter = new BoardAdapter(list, getActivity());
        recyclerView.setAdapter(boardAdapter);

        //А вот здесь ответ на вопрос по запросу к интернету
        if (list.size() < 1) {
            getData();
        }
    }

    @Override
    void getData() {
        if (isOnline()) {
            ApiFactory.getCheckingService()
                    .getRxBoard("pa")
                    .subscribeOn(Schedulers.io())
                    .map(Board::getThreads)
                    .flatMap(boardDBS -> {
                        try (Realm realm = Realm.getDefaultInstance()) {
                            realm.executeTransaction(r -> {
                                r.delete(BoardDB.class);
                                r.insertOrUpdate(boardDBS);//Solved bug, but what is the different between copyToRealmOrUpdate and my method used.
                            });
                        }
                        return Observable.just(boardDBS);
                    })
                    .onErrorResumeNext(throwable -> {
                        try (Realm realm = Realm.getDefaultInstance()) {
                            realm.refresh();
                            //realm.close();
                        }
                    })
                    //.map(boardDBS -> realmResults)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::showList, throwable -> showError());
        }
        /*{ //recyclerView.setAdapter(new BoardAdapter(re, getActivity()));
        showList()}, Throwable::printStackTrace);*/
    }

    private void showError() {
        //textOnline.setVisibility(View.VISIBLE);
        Toast.makeText(getActivity(), "Not Connected", Toast.LENGTH_SHORT).show();
    }

    private void showList(RealmList<BoardDB> boardDBS) {
        boardAdapter.changeDataSet(boardDBS);
    }

    @Override
    void showList() {
        boardAdapter.changeDataSet(list);
    }

    private boolean isOnline() {
        ConnectivityManager manager =
                //подойдет ли getActivity?
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        //Есть еще isConnected - но это если в данный момент времени
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

}
