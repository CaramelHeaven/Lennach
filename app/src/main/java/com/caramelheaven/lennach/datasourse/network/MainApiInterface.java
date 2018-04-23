package com.caramelheaven.lennach.datasourse.network;

import com.caramelheaven.lennach.datasourse.model.Board;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MainApiInterface {

    @GET("{board}/catalog.json")
    Observable<Board> getBoard(@Path("board") String boardView);
}
