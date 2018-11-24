package com.caramelheaven.lennach.models.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

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