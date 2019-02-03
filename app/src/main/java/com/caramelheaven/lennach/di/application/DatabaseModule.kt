package com.caramelheaven.lennach.di.application

import android.arch.persistence.room.Room
import android.content.Context

import com.caramelheaven.lennach.data.datasource.database.LennachDatabase
import com.caramelheaven.lennach.data.datasource.database.dao.BoardDao

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Created by CaramelHeaven on 16:48, 03/02/2019.
 */
@Module
class DatabaseModule {
    private val databaseName = "lennach_db"

    @Provides
    @Singleton
    fun provideLennachDatabase(context: Context): LennachDatabase {
        return Room.databaseBuilder(context, LennachDatabase::class.java, databaseName).build()
    }

    @Provides
    @Singleton
    fun provideBoardDao(lennachDatabase: LennachDatabase): BoardDao {
        return lennachDatabase.boardDao
    }
}
