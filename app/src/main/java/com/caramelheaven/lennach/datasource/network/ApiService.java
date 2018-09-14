package com.caramelheaven.lennach.datasource.network;

import com.caramelheaven.lennach.datasource.model.Board;
import com.caramelheaven.lennach.datasource.model.BoardSettings;
import com.caramelheaven.lennach.datasource.model.Captcha;
import com.caramelheaven.lennach.datasource.model.Post;
import com.caramelheaven.lennach.datasource.model.PostInThread;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by CaramelHeaven on 27.07.2018
 */
public interface ApiService {
    @GET("{boardName}/{page}.json")
    Single<Board> getBoard(@Path("boardName") String boardName,
                           @Path("page") int page);

    @GET("api/captcha/{type}/id")
    Single<Captcha> getCaptcha(@Path("type") String captchaType,
                               @Query("board") String board,
                               @Query("thread") String thread);

    @GET("makaba/mobile.fcgi?task=get_thread")
    Single<List<Post>> getPostsByThreadId(@Query("board") String boardName,
                                          @Query("thread") String threadId,
                                          @Query("num") String numId);



    @Multipart
    @POST("makaba/posting.fcgi?json=1&task=post")
    Single<PostInThread> sendPostInThread(@PartMap Map<String, RequestBody> options);




}
