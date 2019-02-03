package com.caramelheaven.lennach.data.datasource.network

import com.caramelheaven.lennach.models.network.base.BoardResponse
import com.caramelheaven.lennach.models.network.base.PostResponse
import com.caramelheaven.lennach.models.network.list_of_boards.BoardAllResponse

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by CaramelHeaven on 16:44, 03/02/2019.
 */

interface LennachApiService {
    @GET("{boardName}/{page}.json")
    fun getBoard(@Path("boardName") boardName: String,
                 @Path("page") page: Int): Single<BoardResponse>

    @GET("makaba/mobile.fcgi?task=get_thread")
    fun getPostsByThread(@Query("board") boardName: String,
                         @Query("thread") threadId: String,
                         @Query("post") numId: String): Single<List<PostResponse>>

    @GET("makaba/mobile.fcgi?task=get_boards")
    fun getAllBoards(): Single<BoardAllResponse>
}

//    @GET("api/captcha/{type}/id")
//    Single<CaptchaResponse> getCaptcha(@Path("type") String captchaType,
//                                       @Query("board") String board,
//                                       @Query("thread") String thread);
//
//    @Multipart
//    @POST("makaba/posting.fcgi?json=1&task=post")
//    Single<MessagePostResponse> sendMessage(@PartMap Map<String, RequestBody> options);
//
//    @Multipart
//    @POST("makaba/posting.fcgi?json=1&task=post")
//    Single<MessagePostResponse> sendMessageWithImage(@PartMap Map<String, RequestBody> options,
//                                                     @Part MultipartBody.Part file);
//

