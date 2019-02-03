package com.caramelheaven.lennach.models.model

/**
 * Created by CaramelHeaven on 16:19, 03/02/2019.
 */
class Board {
    var board: String? = null
    var boardName: String? = null
    var boardSpeed: Int? = null
    var bumpLimit: Int? = null
    var maxComment: Int? = null
    var maxFileSize: Int? = null
    var pages: List<Int>? = null
    var currentPage: Int? = null
    var usenetList: List<Usenet>? = null
}
