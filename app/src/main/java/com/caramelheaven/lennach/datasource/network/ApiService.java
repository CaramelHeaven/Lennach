package com.caramelheaven.lennach.datasource.network;

import com.caramelheaven.lennach.datasource.model.Board;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by CaramelHeaven on 27.07.2018
 */
public interface ApiService {
    @GET("{boardName}/{page}.json")
    Single<Board> getBoard(@Path("boardName") String boardName,
                           @Path("page") int page);
}
