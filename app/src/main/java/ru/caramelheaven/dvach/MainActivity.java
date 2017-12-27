package ru.caramelheaven.dvach;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.caramelheaven.dvach.data.Board;
import ru.caramelheaven.dvach.data.Thread;
import ru.caramelheaven.dvach.network.DvachClient;


public class MainActivity extends AppCompatActivity {
    public final static String GET_BOARD = "GET_BOARD";

    //private ListView listView;
    private RecyclerView recyclerView;

    //Все перегрузки метода subscribe возвращают объект интерфейса Subscribtion,
    private Subscription subscriptionBoard;

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

/*Attempt to use RxJava
        ApiFactory.getDvachClient()
                .getBoard(boardq)
                .map(Board::getBoard)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        */


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://2ch.hk/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();


        DvachClient dvachClient = retrofit.create(DvachClient.class);
        Call<Board> call = dvachClient.getBoard(boardq);

        /*App.getDvachApi().getBoard(boardq).enqueue(new Callback<Board>() {
            @Override
            public void onResponse(Call<Board> call, Response<Board> response) {
                recyclerView.setAdapter(new BoardAdapter(MainActivity.this, response.body().getThreads()));
            }

            @Override
            public void onFailure(Call<Board> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error :(", Toast.LENGTH_SHORT).show();
            }*/
        call.enqueue(new Callback<Board>() {
            @Override
            public void onResponse(Call<Board> call, Response<Board> response) {
                if (response.isSuccessful()) {
                    recyclerView.setAdapter(new BoardAdapter(MainActivity.this, response.body().getThreads()));
                }
                //recyclerView.setAdapter(new RecyclerViewAdapter(MainActivity.this, re));
                //listView.setAdapter(new GitHubRepoAdapter(MainActivity.this, repos));
            }

            @Override
            public void onFailure(Call<Board> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}