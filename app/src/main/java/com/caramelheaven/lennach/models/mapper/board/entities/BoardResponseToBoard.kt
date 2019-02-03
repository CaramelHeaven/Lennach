package com.caramelheaven.lennach.models.mapper.board.entities

import com.caramelheaven.lennach.models.mapper.Mapper
import com.caramelheaven.lennach.models.model.Board
import com.caramelheaven.lennach.models.model.Usenet
import com.caramelheaven.lennach.models.model.common.DataImage
import com.caramelheaven.lennach.models.network.base.BoardResponse

/**
 * Created by CaramelHeaven on 16:29, 03/02/2019.
 */
class BoardResponseToBoard : Mapper<Board, BoardResponse>() {

    override fun map(enterData: BoardResponse): Board {
        val board = Board()
        fillData(board, enterData)

        return board
    }

    override fun fillData(outputData: Board, enterData: BoardResponse) {
        outputData.currentPage = enterData.currentPage
        outputData.pages = enterData.pages
        outputData.board = enterData.board
        outputData.boardName = enterData.boardName
        outputData.boardSpeed = enterData.boardSpeed
        outputData.bumpLimit = enterData.bumpLimit
        outputData.maxComment = enterData.maxComment
        outputData.maxFileSize = enterData.maxFilesSize

        val usenets = ArrayList<Usenet>()

        for (q in 0 until enterData.threadList!!.size) {
            val usenet = Usenet()
            usenet.filesCount = enterData.threadList[q].filesCount
            usenet.postsCount = enterData.threadList[q].postsCount
            usenet.threadNum = enterData.threadList[q].threadNum

            val dataSet = DataImage()

            dataSet.displayNameImage = enterData.threadList[q]
                    .postsList!![0].files!![0].displayname
            dataSet.heightImage = enterData.threadList[q]
                    .postsList!![0].files!![0].height
            dataSet.widthImage = enterData.threadList[q]
                    .postsList!![0].files!![0].width
            dataSet.sizeImage = enterData.threadList[q]
                    .postsList!![0].files!![0].size
            dataSet.nameImage = enterData.threadList[q]
                    .postsList!![0].files!![0].name
            dataSet.thumbnail = enterData.threadList[q]
                    .postsList!![0].files!![0].thumbnail
            dataSet.path = enterData.threadList[q]
                    .postsList!![0].files!![0].path
            usenet.image = dataSet

            usenet.comment = enterData.threadList[q]
                    .postsList!![0].comment
            usenet.date = enterData.threadList[q]
                    .postsList!![0].date
            usenet.name = enterData.threadList[q]
                    .postsList!![0].name
            usenet.num = enterData.threadList[q]
                    .postsList!![0].num
            usenets.add(usenet)
        }

        outputData.usenetList = usenets
    }

}