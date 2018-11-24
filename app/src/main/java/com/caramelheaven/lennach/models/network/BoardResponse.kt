package com.caramelheaven.lennach.models.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BoardResponse {
    @SerializedName("Board")
    @Expose
    val board: String? = null
    @SerializedName("BoardName")
    @Expose
    val boardName: String? = null
    @SerializedName("board_speed")
    @Expose
    val boardSpeed: Int? = null
    @SerializedName("bump_limit")
    @Expose
    val bumpLimit: Int? = null
    @SerializedName("current_page")
    @Expose
    val currentPage: Int? = null
    @SerializedName("current_thread")
    @Expose
    val currentThread: Int? = null
    @SerializedName("max_comment")
    @Expose
    val maxComment: Int? = null
    @SerializedName("max_files_size")
    @Expose
    val maxFilesSize: Int? = null
    @SerializedName("pages")
    @Expose
    val pages: List<Int>? = null
    @SerializedName("threads")
    @Expose
    val threadList: List<ThreadResponse>? = null
}
