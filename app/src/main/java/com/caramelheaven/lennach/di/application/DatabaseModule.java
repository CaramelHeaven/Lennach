package com.caramelheaven.lennach.di.application;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.caramelheaven.lennach.data.datasource.database.LennachDatabase;
import com.caramelheaven.lennach.data.datasource.database.dao.BoardDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CaramelHeaven on 20:49, 23/01/2019.
 */
@Module
public class DatabaseModule {
    private String databaseName = "lennach_db";

    @Provides
    @Singleton
    public LennachDatabase provideLennachDatabase(Context context) {
        return Room.databaseBuilder(context, LennachDatabase.class, databaseName).build();
    }

    @Provides
    @Singleton
    public BoardDao provideBoardDao(LennachDatabase lennachDatabase) {
        return lennachDatabase.getBoardDao();
    }
}
