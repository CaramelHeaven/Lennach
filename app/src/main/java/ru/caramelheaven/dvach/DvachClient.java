/*package ru.caramelheaven.dvach;

import java.util.Observable;

import io.reactivex.annotations.NonNull;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.caramelheaven.dvach.data.Board;
import ru.caramelheaven.dvach.network.DvachService;
import rx.Scheduler;
import rx.schedulers.Schedulers;

public class DvachClient {

    private static final String BASE_URL = "https://2ch.hk/";

    private static DvachClient dvachClient;
    private DvachService dvachService;

    private DvachClient() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dvachService = retrofit.create(DvachService.class);
    }

    public static DvachClient getDvachClient() {
        if (dvachClient == null) {
            dvachClient = new DvachClient();
        }
        return new DvachClient();
    }

    public io.reactivex.Observable<Board> getBoardMethod(@NonNull String board) {
        return dvachService.getBoard(board);
    }
}*/
