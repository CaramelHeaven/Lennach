package com.caramelheaven.lennach.datasource.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by CaramelHeaven on 27.07.2018
 */
class Board {
    @SerializedName("Board")
    @Expose
    var board: String? = null
    @SerializedName("BoardName")
    @Expose
    var boardName: String? = null
    @SerializedName("board_speed")
    @Expose
    var boardSpeed: Int? = null
    @SerializedName("bump_limit")
    @Expose
    var bumpLimit: Int? = null
    @SerializedName("current_page")
    @Expose
    var currentPage: Int? = null
    @SerializedName("current_thread")
    @Expose
    var currentThread: Int? = null
    @SerializedName("max_comment")
    @Expose
    var maxComment: Int? = null
    @SerializedName("max_files_size")
    @Expose
    var maxFilesSize: Int? = null
    @SerializedName("pages")
    @Expose
    var pages: List<Int>? = null
    @SerializedName("threads")
    @Expose
    var threads: List<Thread>? = null
}
