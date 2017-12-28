package ru.caramelheaven.dvach;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
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

    //Все перегрузки метода subscribe возвращают объект интерфейса Subscribtion,
    private Subscription subscriptionBoard;

    //тип Disposable позволяет вызывать метод dispose, означающий «Я закончил работать с этим ресурсом, мне больше не нужны данные»
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String boardq = intent.getStringExtra(GET_BOARD);

        //listView = (ListView) findViewById(R.id.pagination_list);
        recyclerView = findViewById(R.id.recycle_view);

        List<Thread> repos = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        BoardAdapter adapter = new BoardAdapter(this, repos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BoardAdapter.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });

        DvachService dvachService = new Retrofit.Builder()
                .baseUrl("https://2ch.hk/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(DvachService.class);

        //Call<Board> call = dvachService.getBoard(boardq);

        //Problem with memory BAG
        dvachService.getRxBoard("b")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Board>() {
                    @Override
                    public void onNext(Board board) {
                        recyclerView.setAdapter(new BoardAdapter(MainActivity.this, board.getThreads()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "error :(", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(MainActivity.this, "Completed", Toast.LENGTH_SHORT).show();
                    }
                });
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
            });*/

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
}