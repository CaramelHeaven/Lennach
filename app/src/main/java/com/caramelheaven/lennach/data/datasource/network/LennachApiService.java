package com.caramelheaven.lennach.data.datasource.network;

import com.caramelheaven.lennach.models.model.thread_viewer.Post;
import com.caramelheaven.lennach.models.network.BoardResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LennachApiService {
    @GET("{boardName}/{page}.json")
    Single<BoardResponse> getBoard(@Path("boardName") String boardName,
                                   @Path("page") int page);

    @GET("makaba/mobile.fcgi?task=get_thread")
    Single<List<Post>> getPostsByThread(@Query("board") String boardName,
                                        @Query("thread") String threadId,
                                        @Query("post") String numId);
}
