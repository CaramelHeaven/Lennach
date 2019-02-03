package com.caramelheaven.lennach.models.model

/**
 * Created by CaramelHeaven on 16:19, 03/02/2019.
 * Change to data class
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

    override fun toString(): String {
        return "Board(board=$board, boardName=$boardName, boardSpeed=$boardSpeed, bumpLimit=$bumpLimit, maxComment=$maxComment, maxFileSize=$maxFileSize, pages=$pages, currentPage=$currentPage, usenetList=$usenetList)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Board

        if (board != other.board) return false
        if (boardName != other.boardName) return false
        if (boardSpeed != other.boardSpeed) return false
        if (bumpLimit != other.bumpLimit) return false
        if (maxComment != other.maxComment) return false
        if (maxFileSize != other.maxFileSize) return false
        if (pages != other.pages) return false
        if (currentPage != other.currentPage) return false
        if (usenetList != other.usenetList) return false

        return true
    }

    override fun hashCode(): Int {
        var result = board?.hashCode() ?: 0
        result = 31 * result + (boardName?.hashCode() ?: 0)
        result = 31 * result + (boardSpeed ?: 0)
        result = 31 * result + (bumpLimit ?: 0)
        result = 31 * result + (maxComment ?: 0)
        result = 31 * result + (maxFileSize ?: 0)
        result = 31 * result + (pages?.hashCode() ?: 0)
        result = 31 * result + (currentPage ?: 0)
        result = 31 * result + (usenetList?.hashCode() ?: 0)
        return result
    }


}
