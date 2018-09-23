package com.caramelheaven.lennach.data.datasource.network;

import com.caramelheaven.lennach.models.network.BoardResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LennachApiService {
    @GET("{boardName}/{page}.json")
    Single<BoardResponse> getBoard(@Path("boardName") String boardName,
                                   @Path("page") int page);
}
