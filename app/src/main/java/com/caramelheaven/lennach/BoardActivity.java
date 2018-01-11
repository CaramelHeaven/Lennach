package com.caramelheaven.lennach;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.caramelheaven.lennach.adapters.BoardAdapter;
import com.caramelheaven.lennach.adapters.BoardAdapterRealm;
import com.caramelheaven.lennach.data.Board;
import com.caramelheaven.lennach.database.BoardDB;
import com.caramelheaven.lennach.network.ApiFactory;
import com.caramelheaven.lennach.network.ApiService;
import com.caramelheaven.lennach.database.MyMigration;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BoardActivity extends AppCompatActivity {

    private static final String LOGS = BoardActivity.class.getSimpleName();
    private RecyclerView recyclerBoard;
    private Realm realmUI;
    BoardAdapter adapter;
    private TextView textOnline;
    RealmResults<BoardDB> realmResults;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        realmUI = Realm.getDefaultInstance();

        textOnline = findViewById(R.id.isOnline);
        textOnline.setVisibility(View.GONE);

        setUpRecyclerView();

        if (realmUI.isEmpty()) {
            if (isOnline()) {
                RealmResults<BoardDB> realmResults = realmUI.where(BoardDB.class).findAll();
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
                        .map(boardDBS -> realmResults)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(re -> {
                            recyclerBoard.setAdapter(new BoardAdapterRealm(re, BoardActivity.this));
                        }, Throwable::printStackTrace);
            } else {
                textOnline.setVisibility(View.VISIBLE);
                Toast.makeText(this, "Not Connected", Toast.LENGTH_SHORT).show();
            }
        } else {
            realmResults = realmUI.where(BoardDB.class).findAllAsync();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recyclerBoard.setAdapter(null);
        realmUI.close();
    }

    public boolean isOnline() {
        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        //Есть еще isConnected - но это если в данный момент времени
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    private void setUpRecyclerView() {
        recyclerBoard = findViewById(R.id.recycle_board);
        recyclerBoard.setHasFixedSize(true);
        recyclerBoard.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerBoard.setLayoutManager(layoutManager);
    }
}
