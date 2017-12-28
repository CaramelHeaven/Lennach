package ru.caramelheaven.dvach.fragment_or_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.caramelheaven.dvach.R;
import ru.caramelheaven.dvach.adapters.ThreadAdapter;
import ru.caramelheaven.dvach.data.Board;
import ru.caramelheaven.dvach.data.Post;
import ru.caramelheaven.dvach.network.DvachService;


public class ThreadActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://2ch.hk";
    public static final String NUMBER_THREAD = "NUMBER_THREAD";
    public static final String BOARD = "BOARD";

    private RecyclerView recyclerViewq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        Intent intent = getIntent();
        String currentNumberThread = intent.getStringExtra(NUMBER_THREAD);
        String currentBoard = intent.getStringExtra(BOARD);

        recyclerViewq = findViewById(R.id.recycle_view_thread);

        List<Post> posts = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewq.setHasFixedSize(true);
        recyclerViewq.setLayoutManager(layoutManager);

        DvachService dvachService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(DvachService.class);

        Log.e("MY_LOGS", currentBoard);
        try {
            dvachService.getRxThread(currentBoard, currentNumberThread)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableObserver<Board>() {
                        @Override
                        public void onNext(Board board) {
                            recyclerViewq.setAdapter(new ThreadAdapter(ThreadActivity.this, board.getThreads().get(0).getPosts()));
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(ThreadActivity.this, "error :(", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }

                        @Override
                        public void onComplete() {
                            Toast.makeText(ThreadActivity.this, "Completed", Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        /*Call<Board> call = dvachService.getThread("b", currentNumberThread); // Здесь должен быть GET_BOARD, но он не работает.
        call.enqueue(new Callback<Board>() {
            @Override
            public void onResponse(Call<Board> call, Response<Board> response) {
                if (response.isSuccessful()) {
                    recyclerViewq.setAdapter(new ThreadAdapter(ThreadActivity.this, response.body().getThreads().get(0).getPosts()));
                }
            }

            @Override
            public void onFailure(Call<Board> call, Throwable t) {
                Toast.makeText(ThreadActivity.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}
