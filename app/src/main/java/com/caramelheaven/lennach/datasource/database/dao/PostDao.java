package com.caramelheaven.lennach.datasource.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.caramelheaven.lennach.datasource.database.entity.iPost;

import java.util.List;

/**
 * Created by CaramelHeaven on 29.07.2018
 */
@Dao
public interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPost(iPost iPosts);

    @Update
    void updatePost(iPost iPost);

    @Delete
    void deletePost(iPost iPost);

    @Query("SELECT * FROM iPost")
    List<iPost> getAllPosts();

    @Query("SELECT * FROM iPost WHERE idThread=:threadId")
    List<iPost> findPostsByThread(final int threadId);
}
