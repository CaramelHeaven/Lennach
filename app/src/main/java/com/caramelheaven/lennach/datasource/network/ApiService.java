package com.caramelheaven.lennach.datasource.network;

import com.caramelheaven.lennach.datasource.model.Board;
import com.caramelheaven.lennach.datasource.model.BoardSettings;
import com.caramelheaven.lennach.datasource.model.Captcha;
import com.caramelheaven.lennach.datasource.model.Post;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by CaramelHeaven on 27.07.2018
 */
public interface ApiService {
    @GET("{boardName}/{page}.json")
    Single<Board> getBoard(@Path("boardName") String boardName,
                           @Path("page") int page);

    @GET("api/captcha{type}/id")
    Single<Captcha> getCaptcha(@Path("type") String captchaType,
                               @Query("board") String board,
                               @Query("thread") String thread);

    @GET("makaba/mobile.fcgi?task=get_boards")
    Single<BoardSettings> getBoardSettings();

    @GET("makaba/mobile.fcgi?task=get_thread")
    Single<List<Post>> getPostsByThreadId(@Query("board") String boardName,
                                          @Query("thread") String threadId,
                                          @Query("num") String numId);

}
