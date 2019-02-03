package com.caramelheaven.lennach.models.network.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by CaramelHeaven on 16:16, 03/02/2019.
 */
class ThreadResponse {
    @SerializedName("files_count")
    @Expose
    val filesCount: Int? = null
    @SerializedName("posts")
    @Expose
    val postsList: List<PostResponse>? = null
    @SerializedName("posts_count")
    @Expose
    val postsCount: Int? = null
    @SerializedName("thread_num")
    @Expose
    val threadNum: String? = null
}