package com.caramelheaven.lennach.datasource.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by CaramelHeaven on 27.07.2018
 */
class Thread {
    @SerializedName("files_count")
    @Expose
    var filesCount: Int? = null
    @SerializedName("posts")
    @Expose
    var posts: List<Post>? = null
    @SerializedName("posts_count")
    @Expose
    var postsCount: Int? = null
    @SerializedName("thread_num")
    @Expose
    var threadNum: String? = null
}
