package com.caramelheaven.lennach.data.datasource.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

import com.caramelheaven.lennach.data.datasource.database.dao.BoardDao
import com.caramelheaven.lennach.models.database.BoardDb

/**
 * Created by CaramelHeaven on 16:45, 03/02/2019.
 */
@Database(entities = [BoardDb::class], version = 1, exportSchema = false)
abstract class LennachDatabase : RoomDatabase() {
    abstract val boardDao: BoardDao
}