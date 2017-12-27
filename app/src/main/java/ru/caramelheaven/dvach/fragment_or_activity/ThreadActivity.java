package ru.caramelheaven.dvach.fragment_or_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.caramelheaven.dvach.R;
import ru.caramelheaven.dvach.ThreadAdapter;
import ru.caramelheaven.dvach.data.Board;
import ru.caramelheaven.dvach.data.Post;
import ru.caramelheaven.dvach.network.DvachService;

import static ru.caramelheaven.dvach.BoardAdapter.NUMBER_THREAD;

public class ThreadActivity extends AppCompatActivity {

    private RecyclerView recyclerViewq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        Intent intent = getIntent();
        String f = intent.getStringExtra(NUMBER_THREAD);

        recyclerViewq = findViewById(R.id.recycle_view_thread);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://2ch.hk")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        DvachService dvachService = retrofit.create(DvachService.class);
        Call<Board> call = dvachService.getThread("b", f); // Здесь должен быть GET_BOARD, но он не работает.
        List<Post> posts = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewq.setHasFixedSize(true);
        recyclerViewq.setLayoutManager(layoutManager);

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
        });

    }
}
