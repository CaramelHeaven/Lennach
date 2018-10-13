package com.caramelheaven.lennach.data.datasource.network;

import com.caramelheaven.lennach.models.model.thread_viewer.Post;
import com.caramelheaven.lennach.models.network.BoardResponse;
import com.caramelheaven.lennach.models.network.CaptchaResponse;
import com.caramelheaven.lennach.models.network.MessagePostResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
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

    @GET("api/captcha/{type}/id")
    Single<CaptchaResponse> getCaptcha(@Path("type") String captchaType,
                                       @Query("board") String board,
                                       @Query("thread") String thread);

    @Multipart
    @POST("makaba/posting.fcgi?json=1&task=post")
    Single<MessagePostResponse> sendMessage(@PartMap Map<String, RequestBody> options);

    @Multipart
    @POST("makaba/posting.fcgi?json=1&task=post")
    Single<MessagePostResponse> sendTestImage(@PartMap Map<String, RequestBody> options,
                                              @Part MultipartBody.Part file);
}
