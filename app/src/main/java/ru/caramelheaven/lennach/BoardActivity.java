package ru.caramelheaven.lennach;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmList;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.caramelheaven.lennach.adapters.BoardAdapter;
import ru.caramelheaven.lennach.data.Board;
import ru.caramelheaven.lennach.database.ThreadViewDB;
import ru.caramelheaven.lennach.network.ApiService;

/**
 * Created by Sergey F on 02.01.2018.
 */

public class BoardActivity extends AppCompatActivity {

    private RecyclerView recyclerBoard;
    private Realm realmUI;
    BoardAdapter adapter;
    private static final String GET_BOARD = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        realmUI = Realm.getDefaultInstance();

        Intent intent = getIntent();
        String currentBoard = intent.getStringExtra(GET_BOARD);

        recyclerBoard = findViewById(R.id.recycle_board);
        RealmList<ThreadViewDB> repos = new RealmList<>();
        adapter = new BoardAdapter(repos);
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

        /*service.getRxBoard(currentBoard)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(board -> {
                    recyclerBoard.setAdapter(new BoardAdapter(board.getThreads()));
                });
*/
        service.getRxBoard("b")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(Board::getThreads)
                .flatMap(threadViewDBS -> {
                    try (Realm realm = Realm.getDefaultInstance()) {
                        realm.executeTransaction(r -> {
                            r.delete(ThreadViewDB.class);
                            r.insert(threadViewDBS);
                        });
                        return Observable.just(threadViewDBS);
                    }
                })
                .onErrorResumeNext(throwable -> {
                    try (Realm realm = Realm.getDefaultInstance()) {
                        realm.refresh();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showThreads, throwable -> showError());

        /*service.getRxBoard("b")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(Board::getThreads)
                .flatMap(movies -> {
                    try (Realm realm = Realm.getDefaultInstance()) {
                        realm.executeTransaction(r -> {
                            r.delete(ThreadViewDB.class);
                            r.insert(movies);
                        });
                        return Observable.just(movies);
                    }
                })
                .onErrorResumeNext(throwable -> {
                    Realm realm = Realm.getDefaultInstance();
                    realm.refresh();
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(re -> {
                    recyclerBoard.setAdapter(new BoardAdapterA(board.getThreads()));
                });*/
    }

    private void showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    private void showThreads(RealmList<ThreadViewDB> threadViewDBS) {
        adapter.changeDataSet(threadViewDBS);
        recyclerBoard.setVisibility(View.VISIBLE);
    }

}