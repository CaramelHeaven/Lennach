package com.caramelheaven.lennach.models.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by CaramelHeaven on 16:24, 03/02/2019.
 */
@Entity(tableName = "BoardDb")
public class BoardDb {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int key;

    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "name")
    private String name;

    public BoardDb(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @NonNull
    public int getKey() {
        return key;
    }

    public void setKey(@NonNull int key) {
        this.key = key;
    }
}
