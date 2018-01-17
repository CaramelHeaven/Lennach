package com.caramelheaven.lennach.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import com.caramelheaven.lennach.BoardFragment;
import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.adapters.BoardAdapter;
import android.support.v4.app.Fragment;
import com.caramelheaven.lennach.database.BoardDB;
import io.realm.Realm;
import io.realm.RealmResults;

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

        if (savedInstanceState == null) {
            BoardFragment boardFragment = new BoardFragment();

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.flContainer, boardFragment);
            ft.commit();
        }

        /*realmUI = Realm.getDefaultInstance();

        textOnline = findViewById(R.id.isOnline);
        textOnline.setVisibility(View.GONE);

        setUpRecyclerView();*/

        /*if (realmUI.isEmpty()) {
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
                        //.map(boardDBS -> realmResults)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(re -> {
                            recyclerBoard.setAdapter(new BoardAdapter(re, BoardActivity.this));
                        }, Throwable::printStackTrace);
            } else {
                textOnline.setVisibility(View.VISIBLE);
                Toast.makeText(this, "Not Connected", Toast.LENGTH_SHORT).show();
            }
        } else {
            realmResults = realmUI.where(BoardDB.class).findAllAsync();
        }*/
    }


    @Override
    protected void onStop() {
        super.onStop();
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
