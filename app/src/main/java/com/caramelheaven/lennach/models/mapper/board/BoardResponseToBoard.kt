package com.caramelheaven.lennach.models.mapper.board

import com.caramelheaven.lennach.models.model.board.Board
import com.caramelheaven.lennach.models.model.board.Usenet
import com.caramelheaven.lennach.models.model.common.DataImage
import com.caramelheaven.lennach.models.network.BoardResponse

import java.util.ArrayList

class BoardResponseToBoard {

    fun map(boardResponse: BoardResponse?): Board {
        val board = Board()
        if (boardResponse != null) {
            board.currentPage = boardResponse.currentPage
            board.pages = boardResponse.pages
            board.board = boardResponse.board
            board.boardName = boardResponse.boardName
            board.boardSpeed = boardResponse.boardSpeed
            board.bumpLimit = boardResponse.bumpLimit
            board.maxComment = boardResponse.maxComment
            board.maxFileSize = boardResponse.maxFilesSize
            val usenets = ArrayList<Usenet>()
            for (q in 0 until boardResponse.threadList!!.size) {
                val usenet = Usenet()
                usenet.filesCount = boardResponse.threadList[q].filesCount
                usenet.postsCount = boardResponse.threadList[q].postsCount
                usenet.threadNum = boardResponse.threadList[q].threadNum

                val dataSet = DataImage()
                dataSet.displayNameImage = boardResponse.threadList[q]
                        .postsList!![0].files!![0].displayname
                dataSet.heightImage = boardResponse.threadList[q]
                        .postsList!![0].files!![0].height
                dataSet.widthImage = boardResponse.threadList[q]
                        .postsList!![0].files!![0].width
                dataSet.sizeImage = boardResponse.threadList[q]
                        .postsList!![0].files!![0].size
                dataSet.nameImage = boardResponse.threadList[q]
                        .postsList!![0].files!![0].name
                dataSet.thumbnail = boardResponse.threadList[q]
                        .postsList!![0].files!![0].thumbnail
                dataSet.path = boardResponse.threadList[q]
                        .postsList!![0].files!![0].path
                usenet.image = dataSet

                usenet.comment = boardResponse.threadList[q]
                        .postsList!![0].comment
                usenet.date = boardResponse.threadList[q]
                        .postsList!![0].date
                usenet.name = boardResponse.threadList[q]
                        .postsList!![0].name
                usenet.num = boardResponse.threadList[q]
                        .postsList!![0].num
                usenets.add(usenet)
            }
            board.usenetList = usenets
        }
        return board
    }
}
