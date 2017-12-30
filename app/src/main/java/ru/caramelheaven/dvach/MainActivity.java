package ru.caramelheaven.dvach;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.caramelheaven.dvach.adapters.BoardAdapter;
import ru.caramelheaven.dvach.data.Board;
import ru.caramelheaven.dvach.data.Thread;
import ru.caramelheaven.dvach.network.DvachService;


public class MainActivity extends AppCompatActivity {
    public final static String GET_BOARD = "GET_BOARD";

    //private ListView listView;
    private RecyclerView recyclerView;
    private RealmResults<Thread> data;
    private RealmList<Thread> list;
    private Realm realmq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycle_view);

        Intent intent = getIntent();
        String currentBoard = intent.getStringExtra(GET_BOARD);


        Realm.init(MainActivity.this);
        realmq = Realm.getDefaultInstance();
        data = realmq.where(Thread.class).findAll();

        //listView = (ListView) findViewById(R.id.pagination_list);

        List<Thread> repos = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        Board board = new Board();
        //Second parameter - ir's data from database, our threads
        BoardAdapter adapter = new BoardAdapter(this, data, board.getBoard());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        DvachService dvachService = new Retrofit.Builder()//api
                .baseUrl("https://2ch.hk/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(DvachService.class);

        //Call<Board> call = dvachService.getBoard("pa");
        //Problem with memory BAG
        Log.e("MY_LOGS", currentBoard);

        dvachService.getRxBoard(currentBoard)
                .doOnNext(threads -> {
                    if (threads != null) {
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(threads);
                        realm.commitTransaction();
                        realm.close();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(threads -> threads.isLoaded())
                .subscribeWith(new DisposableObserver<Board>() {
                    @Override
                    public void onNext(Board board) {
                        recyclerView.setAdapter(new BoardAdapter(MainActivity.this, board.getThreads(), board.getBoard()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "error :(", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(MainActivity.this, "Completed", Toast.LENGTH_SHORT).show();
                    }
                })
        ;
        /*call.enqueue(new Callback<Board>() {
            @Override
            public void onResponse(Call<Board> call, Response<Board> response) {
                if (response.isSuccessful()) {
                    recyclerView.setAdapter(new BoardAdapter(MainActivity.this, response.body().getThreads()));
                }
            }

            @Override
            public void onFailure(Call<Board> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });
*/
        /*private void setGetBoard (String board){
            disposable = DvachClient.getDvachClient()
                    .getBoardMethod(GET_BOARD)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((new Consumer<Board>() {
                        @Override
                        public void accept(Board board) throws Exception {
                            recyclerView.setAdapter(MainActivity.class, board.getThreads());
                        }
                    }) {

            })
        }*/
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}