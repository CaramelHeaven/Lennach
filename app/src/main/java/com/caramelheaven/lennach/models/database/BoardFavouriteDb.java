package com.caramelheaven.lennach.models.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by CaramelHeaven on 20:44, 23/01/2019.
 */
@Entity(tableName = "BoardFavouriteDb")
public class BoardFavouriteDb {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int key;

    @ColumnInfo(name = "id")
    private String id;

    public BoardFavouriteDb(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @NonNull
    public int getKey() {
        return key;
    }

    public void setKey(@NonNull int key) {
        this.key = key;
    }
}
