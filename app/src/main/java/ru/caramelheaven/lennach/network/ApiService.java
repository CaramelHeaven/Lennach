package ru.caramelheaven.lennach.network;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.caramelheaven.lennach.data.Board;

/**
 * Created by Sergey F on 04.01.2018.
 */

public interface ApiService {
    @GET("{board}/res/{number}.json")
    Call<Board> getThread(@Path("board") String board, @Path("number") String number);

    @GET("{board}/res/{number}.json")
    Observable<Board> getRxThread(@Path("board") String board, @Path("number") String number);

    @GET("{board}/catalog.json")
    Call<Board> getBoard(@Path("board") String boardView);

    @GET("{board}/catalog.json")
    Observable<Board> getRxBoard(@Path("board") String boardView);

    @GET("{board}/catalog.json")
    Flowable<Board> getFlowableBoard(@Path("board") String boardView);

}