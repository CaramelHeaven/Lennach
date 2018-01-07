package com.caramelheaven.lennach;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.caramelheaven.lennach.adapters.BoardAdapter;
import com.caramelheaven.lennach.data.Board;
import com.caramelheaven.lennach.database.BoardDB;
import com.caramelheaven.lennach.network.ApiService;
import com.caramelheaven.lennach.database.MyMigration;

import java.io.FileNotFoundException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BoardActivity extends AppCompatActivity {

    private static final String LOGS = BoardActivity.class.getSimpleName();
    private RecyclerView recyclerBoard;
    private Realm realmUI;
    BoardAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("lennach1.realm")
                .schemaVersion(1)
                .migration(new MyMigration())
                .build();

        /*RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("lennach.realm")
                .schemaVersion(1)
                .migration(new MyMigration())
                .build();
        */
        realmUI = Realm.getInstance(realmConfig);

        /*Intent intent = getIntent();
        String currentBoard = intent.getStringExtra(GET_BOARD);
*/
        recyclerBoard = findViewById(R.id.recycle_board);
        RealmList<BoardDB> repos = new RealmList<>();

        adapter = new BoardAdapter(BoardActivity.this, repos);

        recyclerBoard.setHasFixedSize(true);
        recyclerBoard.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerBoard.setLayoutManager(layoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://2ch.hk")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        service.getRxBoard("pa")
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
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showThreads, throwable -> showError());

        // Log.d("MY LGOS", currentBoard + " ");
        //.subscribe(this::showThreads, throwable -> showError());
        //Working code
        /*service.getRxBoard("pa")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Board>() {
                    @Override
                    public void onNext(Board board) {
                        recyclerBoard.setAdapter(new BoardAdapter(board.getThreads()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(BoardActivity.this, "error :(", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(BoardActivity.this, "Completed", Toast.LENGTH_SHORT).show();
                    }
                });*/
    }


    private void showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    private void showThreads(RealmList<BoardDB> boardDBS) {
        adapter.changeDataSet(boardDBS);
        recyclerBoard.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
